package mk.ukim.finki.emt.ticket_reservation.company_catalog;

import mk.ukim.finki.emt.ticket_reservation.shared_kernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class CompanyCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyCatalogApplication.class, args);
    }

}



