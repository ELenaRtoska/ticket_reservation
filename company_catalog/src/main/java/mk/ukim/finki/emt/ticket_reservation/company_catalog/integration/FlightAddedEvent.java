package mk.ukim.finki.emt.ticket_reservation.company_catalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.ReservationId;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.FlightId;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.CompanyId;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class FlightAddedEvent implements DomainEvent {

    @JsonProperty("reservationId")
    private final ReservationId reservationId;

    @JsonProperty("flightId")
    private final FlightId flightId;

    @JsonProperty("companyId")
    private final CompanyId companyId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public FlightAddedEvent(ReservationId reservationId, FlightId flightId, CompanyId companyId, Instant occurredOn) {
        this.reservationId = reservationId;
        this.flightId = flightId;
        this.companyId = companyId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

}
