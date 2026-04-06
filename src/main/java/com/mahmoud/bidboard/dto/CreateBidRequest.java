package com.mahmoud.bidboard.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateBidRequest(
    @NotNull Long bidderId,
    @NotNull @DecimalMin("0.01") BigDecimal amount
) {
}
