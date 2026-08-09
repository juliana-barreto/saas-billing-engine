package com.juliana_barreto.saas_billing_engine.modules.invoice.entities;

import com.juliana_barreto.saas_billing_engine.modules.invoice.enums.InvoiceStatus;
import com.juliana_barreto.saas_billing_engine.modules.subscription.entities.Subscription;
import com.juliana_barreto.saas_billing_engine.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tenant_id", "subscription_id", "period_start", "period_end"})
})
public class Invoice extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subscription_id", nullable = false)
  private Subscription subscription;

  @Column(name = "period_start", nullable = false)
  private LocalDate periodStart;

  @Column(name = "period_end", nullable = false)
  private LocalDate periodEnd;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal amount;

  @Column(nullable = false)
  private LocalDate dueDate;

  @Column
  private LocalDate paidDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InvoiceStatus status = InvoiceStatus.PENDING;

  @Column
  private String pdfUrl;
}

