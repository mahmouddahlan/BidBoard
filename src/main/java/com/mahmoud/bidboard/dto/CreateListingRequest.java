package com.mahmoud.bidboard.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CreateListingRequest(
    @NotBlank String title,
    String description,
    @NotNull @DecimalMin(value="0.01", message="starting price must be greater than 0.") BigDecimal startingPrice,
    @NotNull Long sellerId
) {
    
}
