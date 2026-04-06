package com.mahmoud.bidboard.controller;

import com.mahmoud.bidboard.dto.BidResponse;
import com.mahmoud.bidboard.dto.CreateBidRequest;
import com.mahmoud.bidboard.service.BidService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings/{listingId}/bids")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService){
        this.bidService = bidService;
    }

    @PostMapping
    public BidResponse placeBid(@PathVariable Long listingId, @Valid @RequestBody CreateBidRequest request){
        return bidService.placeBid(listingId, request);
    }
    
    @GetMapping
    public List<BidResponse> getBidForListing(@PathVariable Long listingId){
        return bidService.getBidsForListing(listingId);
    }
}
