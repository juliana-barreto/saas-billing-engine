package com.juliana_barreto.saas_billing_engine.modules.subscription.entities;

import com.juliana_barreto.saas_billing_engine.modules.customer.entities.Customer;
import com.juliana_barreto.saas_billing_engine.modules.invoice.entities.Invoice;
import com.juliana_barreto.saas_billing_engine.modules.plan.entities.Plan;
import com.juliana_barreto.saas_billing_engine.modules.subscription.enums.SubscriptionStatus;
import com.juliana_barreto.saas_billing_engine.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Subscription extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_id", nullable = false)
  private Plan currentPlan;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SubscriptionStatus status = SubscriptionStatus.PENDING;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate nextBillingDate;

  @Column
  private LocalDate cancelledAt;

  @OneToMany(mappedBy = "subscription")
  private Set<SubscriptionHistory> history = new HashSet<>();

  @OneToMany(mappedBy = "subscription")
  private Set<Invoice> invoices = new HashSet<>();
}
