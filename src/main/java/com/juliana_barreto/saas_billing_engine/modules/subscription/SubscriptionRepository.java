package com.juliana_barreto.saas_billing_engine.modules.subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

  // Checks if a customer already has an active subscription
  boolean existsByCustomerIdAndStatus(UUID customerId, Subscription status);

  // Brings all active subscriptions for a customer
  @Query("""
      SELECT s FROM Subscription s
      JOIN FETCH s.customer
      JOIN FETCH s.currentPlan
      WHERE s.customer.id = :customerId
      AND s.status = :status
      """)
  List<Subscription> findActiveSubscriptionsWithPlan(
      @Param("customerId") UUID customerId,
      @Param("status") SubscriptionStatus status
  );

  @Query("""
      SELECT s FROM Subscription s
      JOIN FETCH s.customer
      JOIN FETCH s.currentPlan
      WHERE s.status = :status
      AND s.nextBillingDate <= :targetDate
      """)
  List<Subscription> findSubscriptionsDueForBilling(
      @Param("status") SubscriptionStatus status,
      @Param("targetDate") LocalDate targetDate
  );

}
