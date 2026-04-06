package com.mahmoud.bidboard.repository;
import com.mahmoud.bidboard.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository <Listing, Long> {

}
