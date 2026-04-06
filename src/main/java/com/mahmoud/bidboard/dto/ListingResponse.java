package com.mahmoud.bidboard.dto;
import com.mahmoud.bidboard.entity.ListingStatus;
import java.math.BigDecimal;

public record ListingResponse(
    Long id,
    String title,
    String description,
    BigDecimal startingPrice,
    BigDecimal currentPrice,
    ListingStatus status,
    Long sellerId,
    String sellerUsername
) {
    
}
