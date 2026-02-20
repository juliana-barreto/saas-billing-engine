package com.juliana_barreto.saas_billing_engine.modules.plan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, UUID> {

  // Returns all plans currently available for sale
  List<Plan> findAllByActiveTrue();

  // Fetches a specific plan, ensuring it is active
  Optional<Plan> findByIdAndActiveTrue(UUID id);
}
