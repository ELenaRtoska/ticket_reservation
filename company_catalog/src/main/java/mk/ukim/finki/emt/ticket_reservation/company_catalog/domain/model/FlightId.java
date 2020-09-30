package mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class FlightId extends DomainObjectId {

    private String id;

    private FlightId() {
        super(DomainObjectId.randomId(mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.FlightId.class).toString());
    }

    @JsonCreator
    public FlightId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
