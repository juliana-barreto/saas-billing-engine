package com.juliana_barreto.saas_billing_engine.modules.invoice;

import com.juliana_barreto.saas_billing_engine.modules.invoice.enums.InvoiceStatus;
import com.juliana_barreto.saas_billing_engine.modules.subscription.Subscription;
import com.juliana_barreto.saas_billing_engine.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Invoice extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subscription_id", nullable = false)
  private Subscription subscription;

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

