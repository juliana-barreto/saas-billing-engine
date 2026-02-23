package com.juliana_barreto.saas_billing_engine.modules.plan.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PlanResponseDTO(
    UUID id,
    String name,
    BigDecimal price,
    Integer billingCycle,
    boolean active
) {

}
