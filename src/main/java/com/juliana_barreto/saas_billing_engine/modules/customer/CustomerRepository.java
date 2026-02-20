package com.juliana_barreto.saas_billing_engine.modules.customer;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

  Optional<Customer> findByEmail(String email);

  Optional<Customer> findByDocument(String document);

  boolean existsByEmail(String email);

  boolean existsByDocument(String document);

  // Retrieves a customer by ID and eagerly fetches their associated subscriptions
  @Query("""
      SELECT c FROM Customer c
      LEFT JOIN FETCH c.subscriptions
      WHERE c.id = :id
      """)
  Optional<Customer> findByIdWithSubscriptions(@Param("id") UUID id);
}
