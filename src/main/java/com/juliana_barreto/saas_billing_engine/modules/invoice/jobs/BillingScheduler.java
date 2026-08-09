package com.juliana_barreto.saas_billing_engine.modules.invoice.jobs;

import com.juliana_barreto.saas_billing_engine.modules.invoice.services.BillingService;
import com.juliana_barreto.saas_billing_engine.modules.subscription.entities.Subscription;
import com.juliana_barreto.saas_billing_engine.modules.subscription.enums.SubscriptionStatus;
import com.juliana_barreto.saas_billing_engine.modules.subscription.repositories.SubscriptionRepository;
import com.juliana_barreto.saas_billing_engine.shared.infra.multitenancy.TenantContext;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BillingScheduler {

  private final SubscriptionRepository subscriptionRepository;
  private final BillingService billingService;

  @Scheduled(cron = "0 0 1 * * ?")
  public void processRecurringBillings() {
    log.info("Starting recurring billing process...");
    LocalDate today = LocalDate.now();

    List<Subscription> dueSubscriptions = subscriptionRepository
        .findDueForBillingWithRelations(SubscriptionStatus.ACTIVE, today);

    for (Subscription subscription : dueSubscriptions) {
      TenantContext.setTenant(subscription.getTenantId());
      try {
        billingService.generateInvoice(subscription, today);
        log.info("Successfully generated invoice for subscription: {}", subscription.getId());
      } catch (DataIntegrityViolationException e) {
        log.warn("Idempotency triggered: Invoice already exists for subscription {} in this period. Skipping.", subscription.getId());
      } catch (Exception e) {
        log.error("Failed to generate invoice for subscription: {}. Error: {}", subscription.getId(), e.getMessage(), e);
      } finally {
        TenantContext.clear();
      }
    }
    
    log.info("Finished recurring billing process. Processed {} due subscriptions.", dueSubscriptions.size());
  }
}
