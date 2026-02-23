package com.juliana_barreto.saas_billing_engine.modules.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerRequestDTO(
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank(message = "Document is required")
    @Pattern(regexp = "\\d{11}|\\d{14}",
        message = "Document must contain 11 digits (CPF) or 14 digits (CNPJ)")
    String document,

    @NotBlank(message = "Address is required")
    String address
) {

}
