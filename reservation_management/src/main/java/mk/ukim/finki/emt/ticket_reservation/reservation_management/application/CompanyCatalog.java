package mk.ukim.finki.emt.ticket_reservation.reservation_management.application;

import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.Company;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.CompanyId;

import java.util.List;

public interface CompanyCatalog {

    List<Company> findAll();

    Company findById(CompanyId id);


}
