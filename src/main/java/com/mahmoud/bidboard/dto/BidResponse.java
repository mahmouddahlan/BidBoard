package com.mahmoud.bidboard.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BidResponse(
    Long id,
    BigDecimal amount,
    LocalDateTime createdAt,
    long bidderId,
    String bidderUsername,
    Long listingId
) {
    
}
