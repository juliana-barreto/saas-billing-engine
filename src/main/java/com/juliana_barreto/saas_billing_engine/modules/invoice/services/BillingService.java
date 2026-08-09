package com.juliana_barreto.saas_billing_engine.modules.invoice.services;

import com.juliana_barreto.saas_billing_engine.modules.invoice.entities.Invoice;
import com.juliana_barreto.saas_billing_engine.modules.invoice.enums.InvoiceStatus;
import com.juliana_barreto.saas_billing_engine.modules.invoice.repositories.InvoiceRepository;
import com.juliana_barreto.saas_billing_engine.modules.subscription.entities.Subscription;
import com.juliana_barreto.saas_billing_engine.modules.subscription.repositories.SubscriptionRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BillingService {

  private final InvoiceRepository invoiceRepository;
  private final SubscriptionRepository subscriptionRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void generateInvoice(Subscription subscription, LocalDate billingDate) {
    Invoice invoice = new Invoice();
    invoice.setSubscription(subscription);
    invoice.setPeriodStart(subscription.getNextBillingDate());
    
    LocalDate periodEnd = subscription.getNextBillingDate()
        .plusDays(subscription.getCurrentPlan().getBillingCycleDays());
    invoice.setPeriodEnd(periodEnd);
    
    invoice.setAmount(subscription.getCurrentPlan().getPrice());
    invoice.setDueDate(billingDate.plusDays(5));
    invoice.setStatus(InvoiceStatus.PENDING);
    
    invoiceRepository.save(invoice);
    
    subscription.setNextBillingDate(periodEnd);
    subscriptionRepository.save(subscription);
  }
}
