package mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CompanyId extends DomainObjectId {

    private CompanyId() {
        super(DomainObjectId.randomId(mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.CompanyId.class).toString());
    }

    @JsonCreator
    public CompanyId(String id) {
        super(id);
    }

}
