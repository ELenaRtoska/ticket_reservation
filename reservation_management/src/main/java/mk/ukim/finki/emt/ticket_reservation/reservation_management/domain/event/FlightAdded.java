package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.ReservationId;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.FlightId;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.CompanyId;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class FlightAdded implements DomainEvent {

    @JsonProperty("reservationId")
    private final ReservationId reservationId;

    @JsonProperty
    private final FlightId flightId;

    @JsonProperty
    private final CompanyId companyId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public FlightAdded(ReservationId reservationId, FlightId flightId, CompanyId companyId, int quantity, Instant occurredOn) {
        this.reservationId = reservationId;
        this.flightId = flightId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }


}
