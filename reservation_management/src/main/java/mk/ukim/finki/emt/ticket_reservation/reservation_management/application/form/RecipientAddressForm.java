package mk.ukim.finki.emt.ticket_reservation.reservation_management.application.form;

import lombok.Data;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.geo.CityName;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.geo.Country;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RecipientAddressForm implements Serializable {

    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotNull
    private CityName city;
    @NotNull
    private Country country;


}
