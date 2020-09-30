package mk.ukim.finki.emt.ticket_reservation.company_catalog.application;

import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.Company;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.CompanyId;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.repository.CompanyRepository;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.integration.FlightAddedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CompanyCatalog {

    private final CompanyRepository companyRepository;

    public CompanyCatalog(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @NonNull
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @NonNull
    public Optional<Company> findById(@NonNull CompanyId companyId) {
        Objects.requireNonNull(companyId, "companyId must not be null");
        return companyRepository.findById(companyId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onReservationCreatedEvent(FlightAddedEvent event) {
        Company p = companyRepository.findById(event.getCompanyId()).orElseThrow(RuntimeException::new);
        //p.subtractQuantity(event.getQuantity());
        companyRepository.save(p);
    }
}
