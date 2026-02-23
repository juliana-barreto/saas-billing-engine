package com.juliana_barreto.saas_billing_engine.modules.subscription.entities;

import com.juliana_barreto.saas_billing_engine.modules.subscription.enums.SubscriptionChangeType;
import com.juliana_barreto.saas_billing_engine.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Immutable
public class SubscriptionHistory extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subscription_id", nullable = false)
  private Subscription subscription;

  @Enumerated(EnumType.STRING)
  private SubscriptionChangeType changeType;

  @Column(name = "old_plan_price", precision = 19, scale = 4, nullable = false)
  private BigDecimal oldPlanPriceSnapshot;

  @Column(name = "new_plan_price", precision = 19, scale = 4, nullable = false)
  private BigDecimal newPlanPriceSnapshot;
}
