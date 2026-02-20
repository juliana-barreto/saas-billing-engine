package com.juliana_barreto.saas_billing_engine.modules.subscription_history;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory, UUID> {

  Optional<SubscriptionHistory> findFirstBySubscriptionIdOrderByCreatedAtDesc(UUID subscriptionId);

}
