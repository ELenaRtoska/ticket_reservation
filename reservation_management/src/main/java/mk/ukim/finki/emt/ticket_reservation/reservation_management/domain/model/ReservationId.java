package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class ReservationId extends DomainObjectId {

    private ReservationId() {
        super("");
    }

    public ReservationId(@NonNull String id) {
        super(id);
    }
}
