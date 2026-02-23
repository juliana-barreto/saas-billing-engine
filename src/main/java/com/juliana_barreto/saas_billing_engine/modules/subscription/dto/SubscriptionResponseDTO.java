package com.juliana_barreto.saas_billing_engine.modules.subscription.dto;

import java.time.LocalDate;
import java.util.UUID;

public record SubscriptionResponseDTO(
    UUID id,
    String customerName, // Friendly data for the UI
    String planName,     // Friendly data for the UI
    String status,
    LocalDate nextBillingDate
) {

}
