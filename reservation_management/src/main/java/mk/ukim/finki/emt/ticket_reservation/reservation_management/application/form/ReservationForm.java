package mk.ukim.finki.emt.ticket_reservation.reservation_management.application.form;

import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservationForm implements Serializable {

    @NotNull
    private Currency currency;

    @Valid
    @NotNull
    private RecipientAddressForm billingAddress = new RecipientAddressForm();

    @Valid
    @NotEmpty
    private List<FlightForm> items = new ArrayList<>();

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public RecipientAddressForm getBillingAddress() {
        return billingAddress;
    }

    public List<FlightForm> getItems() {
        return items;
    }
}
