package com.juliana_barreto.SubscriptionEngine.modules.customer;

import com.juliana_barreto.SubscriptionEngine.modules.subscription.Subscription;
import com.juliana_barreto.SubscriptionEngine.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.io.Serial;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String document;

  @Column(nullable = false)
  private String address;

  @OneToMany(mappedBy = "customer")
  private Set<Subscription> subscriptions = new HashSet<>();
}
