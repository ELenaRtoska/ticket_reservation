package mk.ukim.finki.emt.ticket_reservation.reservation_management.port.client;

import mk.ukim.finki.emt.ticket_reservation.reservation_management.application.CompanyCatalog;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.Company;
import mk.ukim.finki.emt.ticket_reservation.reservation_management.domain.model.CompanyId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
class CompanyCatalogClient implements CompanyCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyCatalogClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    CompanyCatalogClient(@Value("${app.company_catalog.url}") String serverUrl,
                         @Value("${app.company_catalog.connect-timeout-ms}") int connectTimeout,
                         @Value("${app.company_catalog.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<Company> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/companies").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Company>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving companies", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Company findById(CompanyId id) {
        try {
            return restTemplate.exchange(uri().path("/api/companies/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Company>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving company by id", ex);
            return null;
        }
    }
}
