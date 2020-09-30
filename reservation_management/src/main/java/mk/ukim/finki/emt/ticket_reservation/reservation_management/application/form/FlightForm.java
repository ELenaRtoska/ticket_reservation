package mk.ukim.finki.emt.ticket_reservation.reservation_management.application.form;

import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.Company;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FlightForm implements Serializable {

    @NotNull
    private Company company;

    @Min(1)
    private int quantity = 1;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
