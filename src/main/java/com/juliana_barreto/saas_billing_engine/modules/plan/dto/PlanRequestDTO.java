package com.juliana_barreto.saas_billing_engine.modules.plan.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PlanRequestDTO(
    @NotBlank(message = "Plan name is required")
    String name,

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price cannot be negative")
    BigDecimal price,

    @NotNull(message = "Billing cycle is required")
    @Min(value = 1, message = "Cycle must be at least 1 day")
    Integer billingCycleDays
) {

}
