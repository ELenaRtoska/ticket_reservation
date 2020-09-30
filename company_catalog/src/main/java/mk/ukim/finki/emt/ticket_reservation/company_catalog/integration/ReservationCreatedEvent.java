package mk.ukim.finki.emt.ticket_reservation.company_catalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.ticket_reservation.company_catalog.domain.model.ReservationId;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

public class ReservationCreatedEvent implements DomainEvent {

    @JsonProperty("reservationId")
    private final ReservationId reservationId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public ReservationCreatedEvent(@JsonProperty("reservationId") @NonNull ReservationId reservationId,
                                   @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.reservationId = Objects.requireNonNull(reservationId, "reservationId must not be null");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    @NonNull
    public ReservationId reservationId() {
        return reservationId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}
