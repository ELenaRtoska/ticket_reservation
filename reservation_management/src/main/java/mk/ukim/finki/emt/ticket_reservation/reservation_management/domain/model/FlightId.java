package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;


import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class FlightId extends DomainObjectId {

    private String id;

    private FlightId() {
        super("");
    }

    public FlightId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
