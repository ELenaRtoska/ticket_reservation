package mk.ukim.finki.emt.ticket_reservation.company_catalog.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainEvent;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ReservationCreatedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    ReservationCreatedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("mk.ukim.finki.emt.ticket_reservation.company_catalog.integration.ReservationCreatedEvent");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, ReservationCreatedEvent.class));
    }
}
