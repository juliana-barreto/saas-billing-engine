package com.juliana_barreto.saas_billing_engine.shared.infra.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

  @Override
  public String resolveCurrentTenantIdentifier() {
    String tenantId = TenantContext.getTenant();

    //
    return tenantId != null ? tenantId : "DEFAULT_TENANT";
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}