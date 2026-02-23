package com.juliana_barreto.saas_billing_engine.modules.subscription.repositories;

import com.juliana_barreto.saas_billing_engine.modules.subscription.entities.Subscription;
import com.juliana_barreto.saas_billing_engine.modules.subscription.enums.SubscriptionStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

  // Checks if the customer already has an active subscription
  boolean existsByCustomerIdAndStatus(UUID customerId, Subscription status);

  // Retrieves active subscriptions for a quick status check
  List<Subscription> findByCustomerIdAndStatus(UUID customerId, SubscriptionStatus status);

  // Fetches the subscription with customer and plan
  @Query("""
      SELECT s FROM Subscription s
      JOIN FETCH s.customer
      JOIN FETCH s.currentPlan
      WHERE s.customer.id = :customerId
      AND s.status = :status
      """)
  List<Subscription> findActiveSubscriptionsWithRelations(
      @Param("customerId") UUID customerId,
      @Param("status") SubscriptionStatus status
  );

  // Fetches all subscriptions due for billing today, including plan and customer data
  @Query("""
      SELECT s FROM Subscription s
      JOIN FETCH s.customer
      JOIN FETCH s.currentPlan
      WHERE s.status = :status
      AND s.nextBillingDate <= :targetDate
      """)
  List<Subscription> findDueForBillingWithRelations(
      @Param("status") SubscriptionStatus status,
      @Param("targetDate") LocalDate targetDate
  );

}
