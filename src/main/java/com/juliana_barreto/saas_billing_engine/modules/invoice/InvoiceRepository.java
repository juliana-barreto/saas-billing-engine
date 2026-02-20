package com.juliana_barreto.saas_billing_engine.modules.invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

  List<Invoice> findByStatusAndDueDateLessThan(InvoiceStatus status, LocalDate date);

  @Query("""
          SELECT i FROM Invoice i
          JOIN FETCH i.subscription s 
          JOIN FETCH s.customer 
          JOIN FETCH s.currentPlan 
          WHERE i.id = :id
      """)
  Optional<Invoice> findByIdWithFullDetails(@Param("id") UUID id);
}
