package mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base;


import mk.ukim.finki.emt.ticket_reservation.shared_kernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
