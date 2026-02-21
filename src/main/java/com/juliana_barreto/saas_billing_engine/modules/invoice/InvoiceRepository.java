package com.juliana_barreto.saas_billing_engine.modules.invoice;

import com.juliana_barreto.saas_billing_engine.modules.invoice.enums.InvoiceStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

  // Fetches pending invoices that have passed their due date
  List<Invoice> findByStatusAndDueDateLessThan(InvoiceStatus status, LocalDate date);

  // When sending the invoice email, we need the customer's name and plan details
  @Query("""
          SELECT i FROM Invoice i
          JOIN FETCH i.subscription s
          JOIN FETCH s.customer
          JOIN FETCH s.currentPlan
          WHERE i.id = :id
      """)
  Optional<Invoice> findByIdWithFullDetails(@Param("id") UUID id);
}
