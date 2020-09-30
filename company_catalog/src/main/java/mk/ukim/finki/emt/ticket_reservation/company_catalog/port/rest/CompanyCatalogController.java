package mk.ukim.finki.emt.ticket_reservation.company_catalog.port.rest;

import mk.ukim.finki.emt.ticket_reservation.company_catalog.application.CompanyCatalog;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.Company;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.CompanyId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
class CompanyCatalogController {

    private final CompanyCatalog companyCatalog;

    CompanyCatalogController(CompanyCatalog companyCatalog) {
        this.companyCatalog = companyCatalog;
    }

    // Please note: in a real-world application it would be better to have separate DTO classes that are serialized
    // to JSON. However, to save time, we're using the entity classes directly in this example.

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") String companyId) {
        return companyCatalog.findById(new CompanyId(companyId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Company> findAll() {
        return companyCatalog.findAll();
    }
}
