package com.juliana_barreto.saas_billing_engine.modules.invoice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record InvoiceResponseDTO(
    UUID id,
    UUID subscriptionId,
    BigDecimal amount,
    LocalDate dueDate,
    String status
) {

}
