package com.mahmoud.bidboard.service;

import com.mahmoud.bidboard.dto.CreateListingRequest;
import com.mahmoud.bidboard.dto.ListingResponse;
import com.mahmoud.bidboard.entity.Listing;
import com.mahmoud.bidboard.entity.ListingStatus;
import com.mahmoud.bidboard.entity.User;
import com.mahmoud.bidboard.exception.ResourceNotFoundException;
import com.mahmoud.bidboard.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserService userService;

    public ListingService(ListingRepository listingRepository, UserService userService) {
        this.listingRepository = listingRepository;
        this.userService = userService;
    }

    public ListingResponse createListing(CreateListingRequest request) {
        User seller = userService.getUserEntityById(request.sellerId());

        Listing listing = Listing.builder()
                .title(request.title())
                .description(request.description())
                .startingPrice(request.startingPrice())
                .currentPrice(request.startingPrice())
                .status(ListingStatus.OPEN)
                .seller(seller)
                .build();

        Listing saved = listingRepository.save(listing);
        return mapToResponse(saved);
    }

    public List<ListingResponse> getAllListings() {
        return listingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ListingResponse getListingById(Long id) {
        return mapToResponse(getListingEntityById(id));
    }

    public Listing getListingEntityById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found with id: " + id));
    }

    public ListingResponse closeListing(Long id) {
        Listing listing = getListingEntityById(id);
        listing.setStatus(ListingStatus.CLOSED);
        Listing updated = listingRepository.save(listing);
        return mapToResponse(updated);
    }

    public ListingResponse mapToResponse(Listing listing) {
        return new ListingResponse(
                listing.getId(),
                listing.getTitle(),
                listing.getDescription(),
                listing.getStartingPrice(),
                listing.getCurrentPrice(),
                listing.getStatus(),
                listing.getSeller().getId(),
                listing.getSeller().getUsername()
        );
    }
}