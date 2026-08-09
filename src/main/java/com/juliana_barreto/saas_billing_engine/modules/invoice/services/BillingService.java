package com.juliana_barreto.saas_billing_engine.modules.invoice.services;

import com.juliana_barreto.saas_billing_engine.modules.invoice.entities.Invoice;
import com.juliana_barreto.saas_billing_engine.modules.invoice.enums.InvoiceStatus;
import com.juliana_barreto.saas_billing_engine.modules.invoice.repositories.InvoiceRepository;
import com.juliana_barreto.saas_billing_engine.modules.subscription.entities.Subscription;
import com.juliana_barreto.saas_billing_engine.modules.subscription.repositories.SubscriptionRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    
    LocalDate periodStart = subscription.getNextBillingDate();
    invoice.setPeriodStart(periodStart);
    
    int cycleDays = subscription.getCurrentPlan().getBillingCycleDays();
    LocalDate periodEnd = periodStart.plusDays(cycleDays);
    invoice.setPeriodEnd(periodEnd);
    
    BigDecimal planPrice = subscription.getCurrentPlan().getPrice();
    long actualDays = ChronoUnit.DAYS.between(periodStart, periodEnd);
    BigDecimal amount = calculateProratedAmount(planPrice, cycleDays, actualDays);
    
    invoice.setAmount(amount);
    invoice.setDueDate(billingDate.plusDays(5));
    invoice.setStatus(InvoiceStatus.PENDING);
    
    invoiceRepository.save(invoice);
    
    subscription.setNextBillingDate(periodEnd);
    subscriptionRepository.save(subscription);
  }

  private BigDecimal calculateProratedAmount(BigDecimal fullPrice, int cycleDays, long actualDays) {
    if (actualDays == cycleDays) {
      return fullPrice;
    }
    BigDecimal dailyRate = fullPrice.divide(BigDecimal.valueOf(cycleDays), 4, RoundingMode.HALF_UP);
    return dailyRate.multiply(BigDecimal.valueOf(actualDays)).setScale(2, RoundingMode.HALF_UP);
  }
}
