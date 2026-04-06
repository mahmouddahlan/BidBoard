package com.mahmoud.bidboard.service;

import com.mahmoud.bidboard.dto.BidResponse;
import com.mahmoud.bidboard.dto.CreateBidRequest;
import com.mahmoud.bidboard.entity.Bid;
import com.mahmoud.bidboard.entity.Listing;
import com.mahmoud.bidboard.entity.ListingStatus;
import com.mahmoud.bidboard.entity.User;
import com.mahmoud.bidboard.exception.BadRequestException;
import com.mahmoud.bidboard.repository.BidRepository;
import com.mahmoud.bidboard.repository.ListingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BidService {

    private final BidRepository bidRepository;
    private final ListingService listingService;
    private final UserService userService;
    private final ListingRepository listingRepository;

    public BidService(
            BidRepository bidRepository,
            ListingService listingService,
            UserService userService,
            ListingRepository listingRepository
    ) {
        this.bidRepository = bidRepository;
        this.listingService = listingService;
        this.userService = userService;
        this.listingRepository = listingRepository;
    }
               
    @Transactional
    public BidResponse placeBid(Long listingId, CreateBidRequest request) {
        Listing listing = listingService.getListingEntityById(listingId);
        User bidder = userService.getUserEntityById(request.bidderId());

        if (listing.getStatus() == ListingStatus.CLOSED) {
            throw new BadRequestException("Cannot bid on a closed listing");
        }

        if (request.amount().compareTo(listing.getCurrentPrice()) <= 0) {
            throw new BadRequestException("Bid must be higher than current price");
        }

        Bid bid = Bid.builder()
                .amount(request.amount())
                .createdAt(LocalDateTime.now())
                .bidder(bidder)
                .listing(listing)
                .build();

        Bid savedBid = bidRepository.save(bid);

        listing.setCurrentPrice(request.amount());
        listingRepository.save(listing);

        return mapToResponse(savedBid);
    }

    public List<BidResponse> getBidsForListing(Long listingId) {
        listingService.getListingEntityById(listingId);

        return bidRepository.findByListingIdOrderByAmountDesc(listingId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private BidResponse mapToResponse(Bid bid) {
        return new BidResponse(
                bid.getId(),
                bid.getAmount(),
                bid.getCreatedAt(),
                bid.getBidder().getId(),
                bid.getBidder().getUsername(),
                bid.getListing().getId()
        );
    }
}