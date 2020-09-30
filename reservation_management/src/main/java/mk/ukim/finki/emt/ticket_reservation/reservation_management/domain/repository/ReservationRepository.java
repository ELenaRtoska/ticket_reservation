package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.repository;

import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.Reservation;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
}
