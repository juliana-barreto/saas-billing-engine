package com.juliana_barreto.saas_billing_engine.modules.plan;

import com.juliana_barreto.saas_billing_engine.modules.subscription.Subscription;
import com.juliana_barreto.saas_billing_engine.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Plan extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column
  private String description;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(nullable = false)
  private Integer billingCycleDays;

  @Column(nullable = false)
  private Boolean active;

  @OneToMany(mappedBy = "currentPlan")
  private Set<Subscription> subscriptions;
}
