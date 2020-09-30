package mk.ukim.finki.emt.ticket_reservation.reservation_management;

import mk.ukim.finki.emt.ticket_reservation.shared_kernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.xml.validation.Validator;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class ReservationManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationManagementApplication.class, args);
    }

}

