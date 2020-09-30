package mk.ukim.finki.emt.ticket_reservation.reservation_management.application;

import mk.ukim.finki.emt.ticket_reservation.reservation_management.application.form.ReservationForm;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.event.ReservationCreated;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.event.FlightAdded;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.*;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.repository.ReservationRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ReservationCatalog {

    private final ReservationRepository reservationRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    private CompanyCatalog companyCatalog;

    public ReservationCatalog(ReservationRepository reservationRepository,
                              CompanyCatalog companyCatalog,
                              Validator validator,
                              ApplicationEventPublisher applicationEventPublisher) {
        this.reservationRepository = reservationRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.companyCatalog = companyCatalog;
    }

    public ReservationId createReservation(@NonNull ReservationForm reservation) {
        Objects.requireNonNull(reservation,"reservation must not be null");
        var constraintViolations = validator.validate(reservation);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The ReservationForm is not valid", constraintViolations);
        }

        var newReservation = reservationRepository.saveAndFlush(toDomainModel(reservation));
        applicationEventPublisher.publishEvent(new ReservationCreated(newReservation.id(),newReservation.getReservationOn()));
        newReservation.getItems().forEach(reservationItem -> applicationEventPublisher.publishEvent(new FlightAdded(newReservation.id(),reservationItem.id(),reservationItem.getCompanyId(),reservationItem.getNumberOfCards(), Instant.now())));
        return newReservation.id();
    }

    @NonNull
    public Optional<Reservation> findById(@NonNull ReservationId reservationId) {
        Objects.requireNonNull(reservationId, "reservationId must not be null");
        return reservationRepository.findById(reservationId);
    }

    @NonNull
    private Reservation toDomainModel(@NonNull ReservationForm reservationForm) {
        var reservation = new Reservation(Instant.now(), reservationForm.getCurrency(),
                toDomainModel(reservationForm.getBillingAddress()));
        reservationForm.getItems().forEach(item -> reservation.addItem(item.getCompany(), item.getQuantity()));
        return reservation;
    }

    @NonNull
    private RecipientAddress toDomainModel(@NonNull RecipientAddressForm form) {
        return new RecipientAddress(form.getName(), form.getAddress(),form.getCity(), form.getCountry());
    }



}
