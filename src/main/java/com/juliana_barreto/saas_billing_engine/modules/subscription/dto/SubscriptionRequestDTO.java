package com.juliana_barreto.saas_billing_engine.modules.subscription.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record SubscriptionRequestDTO(
    @NotNull(message = "Customer ID is required")
    UUID customerId,

    @NotNull(message = "Plan ID is required")
    UUID planId
) {

}
