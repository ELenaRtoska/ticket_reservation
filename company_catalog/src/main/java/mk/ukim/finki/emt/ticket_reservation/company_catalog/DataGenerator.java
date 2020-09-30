package mk.ukim.finki.emt.ticket_reservation.company_catalog;

import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.Company;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.CompanyId;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.repository.CompanyRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
class DataGenerator {

    private final CompanyRepository companyRepository;

    DataGenerator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        if (companyRepository.findAll().size()==0) {
            var companies = new ArrayList<Company>();
            companies.add(createCompany(new CompanyId("1"),"Company 1",  "Manager 1"));
            companies.add(createCompany(new CompanyId("2"),"Company 2",  "Manager 2"));
            companies.add(createCompany(new CompanyId("3"),"Company 3",  "Manager 3"));
            companyRepository.saveAll(companies);
        }

    }

    private Company createCompany(CompanyId companyId, String name, String manager) {
        var company = new Company(companyId,name,manager);
        return company;
    }
}
