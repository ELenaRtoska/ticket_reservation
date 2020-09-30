package mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.repository;

import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.Company;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.CompanyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, CompanyId> {
}
