package com.mahmoud.bidboard.repository;

import java.util.List;

import com.mahmoud.bidboard.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository  extends JpaRepository<Bid, Long> {
    List<Bid> findByListingIdOrderByAmountDesc(Long listingId);    
}
