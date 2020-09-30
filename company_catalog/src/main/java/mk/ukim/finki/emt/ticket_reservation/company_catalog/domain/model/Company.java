package mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="company")
public class Company extends AbstractEntity<CompanyId> {

    @Version
    private Long version;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager", nullable = false)
    private String manager;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Company() {

    }

    public Company(CompanyId companyId, String name, String manager) {
        super(companyId);
        this.name = name;
        this.manager=manager;
    }


    public Company(String name, String manager) {
        this.name = name;
        this.manager=manager;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setManager(String manager) {
        this.manager=manager;
    }

    public String getManager() {
        return manager;
    }


}
