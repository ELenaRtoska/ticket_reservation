package mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.ticket_reservation.shared_kernel.domain.financial.Money;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="flights")
@Getter
public class Flight extends AbstractEntity<FlightId> {

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="company_id",nullable = false))
    private CompanyId companyId;

    @Embedded
    private Money price;

    @Column(name = "numberOfCards", nullable = false)
    private int numberOfCards;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private Time time;

    @Column(name = "airPlaneName", nullable = false)
    private String airPlaneName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "fromCity", nullable = false)
    private String fromCity;

    @Column(name = "toCity", nullable = false)
    private String toCity;

    private Flight() {
    }

    Flight(@NonNull CompanyId companyId, @NonNull Money price, @NonNull int numberOfCards,
           Date date, Time time, String airPlaneName, String name, int duration, String fromCity, String toCity)
    {
        super(DomainObjectId.randomId(FlightId.class));
        setCompanyId(companyId);
        setPrice(price);
        this.date=date;
        this.time=time;
        this.airPlaneName=airPlaneName;
        this.name=name;
        this.duration=duration;
        this.fromCity=fromCity;
        this.toCity=toCity;
        if (numberOfCards < 0) {
            throw new IllegalArgumentException("Number Of Cards cannot be negative");
        }
        this.numberOfCards = numberOfCards;
    }


    Flight(@NonNull CompanyId companyId, @NonNull int quantity) {
        super(DomainObjectId.randomId(FlightId.class));
        setCompanyId(companyId);
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.numberOfCards = quantity;
    }


    public void setCompanyId(CompanyId companyId) {
        this.companyId = companyId;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setNumberOfCards(int numberOfCards) {
        if (numberOfCards < 0) {
            throw new IllegalArgumentException("Number Of Cards cannot be negative");
        }
        this.numberOfCards = numberOfCards;
    }

    @NonNull
    @JsonProperty("numberOfCards")
    public int numberOfCards() {
        return numberOfCards;
    }

    public Money subtotal() {
        return price.multiply(numberOfCards);
    }



}
