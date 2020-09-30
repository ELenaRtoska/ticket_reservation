package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="clients")
@Getter
public class Client extends AbstractEntity<ClientId> {
    private String username;
    private String password;
}
