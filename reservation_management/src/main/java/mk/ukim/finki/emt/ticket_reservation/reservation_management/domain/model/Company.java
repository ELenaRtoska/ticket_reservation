package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.financial.Money;

@Getter
public class Company {

    private String name;

    private CompanyId id;

    private String manager;

}
