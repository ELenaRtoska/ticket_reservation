package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ClientId extends DomainObjectId {

    private ClientId() {
        super(DomainObjectId.randomId(ClientId.class).toString());
    }

    @JsonCreator
    public ClientId(String id) {
        super(id);
    }


}
