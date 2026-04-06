package com.mahmoud.bidboard.controller;

import com.mahmoud.bidboard.dto.CreateListingRequest;
import com.mahmoud.bidboard.dto.ListingResponse;
import com.mahmoud.bidboard.entity.Listing;
import com.mahmoud.bidboard.service.ListingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.mahmoud.bidboard.service.ListingService;

@RestController
@RequestMapping("/api/listings")
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ListingResponse createListing(@Valid @RequestBody CreateListingRequest request) {
        return listingService.createListing(request);
    }

    @GetMapping
    public List<ListingResponse> getAllListings(){
        return listingService.getAllListings();
    }

    @GetMapping("/{id}")
    public ListingResponse getListingById(@PathVariable Long id){
        return listingService.getListingById(id);
    }   

    @PatchMapping("/{id}/close")
    public ListingResponse closeListing(@PathVariable Long id){
        return listingService.closeListing(id);
    }
}
