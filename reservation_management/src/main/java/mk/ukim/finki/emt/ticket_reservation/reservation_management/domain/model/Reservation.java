package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.financial.Currency;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.financial.Money;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "reservations")
@Getter
public class Reservation extends AbstractEntity<ReservationId> {

    @Version
    private Long version;

    @Column(name = "reservation_on", nullable = false)
    private Instant reservationOn;

    @Column(name = "reservation_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "reservation_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationState state;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "billing_name", nullable = false)),
            @AttributeOverride(name = "address", column = @Column(name = "billing_address", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city", nullable = false)),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country", nullable = false))
    })
    private RecipientAddress billingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Flight> items;

    @SuppressWarnings("unused")
    private Reservation() {

    }

    public Reservation(@NonNull Instant reservationOn, @NonNull Currency currency, @NonNull RecipientAddress billingAddress) {
        super(DomainObjectId.randomId(ReservationId.class));
        this.items = new HashSet<>();
        setCurrency(currency);
        setReservationOn(reservationOn);
        setState(ReservationState.RECEIVED);
        setBillingAddress(billingAddress);
    }

    @NonNull
    @JsonProperty("state")
    public ReservationState state() {
        return state;
    }

    private void setState(@NonNull ReservationState state) {
        this.state = state;
    }

    public void setReservationOn(Instant reservationOn) {
        this.reservationOn = reservationOn;
    }

    public void setBillingAddress(RecipientAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Stream<Flight> getItems() {
        return items.stream();
    }


    public Flight addItem(@NonNull Company company, Money price, int numberOfCards,
                          Date date, Time time, String airPlaneName, String name, int duration, String fromCity, String toCity) {
        Objects.requireNonNull(company, "company must not be null");
        var item = new Flight(company.getId(), price,numberOfCards,date,time,airPlaneName,name,duration,fromCity,toCity);
        item.setNumberOfCards(numberOfCards);
        items.add(item);
        return item;
    }


    public Flight addItem(@NonNull Company company, int qty) {
        Objects.requireNonNull(company, "company must not be null");
        var item = new Flight(company.getId(), qty);
        item.setNumberOfCards(qty);
        items.add(item);
        return item;
    }



    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    public Money total() {
        return items.stream().map(Flight::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public Instant getReservationOn() {
        return reservationOn;
    }
}
