package com.juliana_barreto.saas_billing_engine.modules.invoice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record InvoiceRequestDTO(
    @NotNull(message = "Subscription ID is mandatory.")
    UUID subscriptionId,

    @NotNull(message = "Amount is required.")
    @DecimalMin(value = "0.01", message = "Amount must be positive.")
    BigDecimal amount,

    @NotNull(message = "Due date is mandatory.")
    @FutureOrPresent(message = "Due date cannot be in the past.")
    LocalDate dueDate
) {

}
