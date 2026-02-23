package com.juliana_barreto.saas_billing_engine.modules.customer.dto;

import java.util.UUID;

public record CustomerResponseDTO(
    UUID id,
    String name,
    String email
) {

}
