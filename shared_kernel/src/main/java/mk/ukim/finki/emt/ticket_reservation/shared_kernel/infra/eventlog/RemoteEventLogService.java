package mk.ukim.finki.emt.ticket_reservation.shared_kernel.infra.eventlog;


import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
