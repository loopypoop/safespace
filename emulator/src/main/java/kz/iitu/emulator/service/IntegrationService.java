package kz.iitu.emulator.service;

import kz.iitu.emulator.model.Indicator;
import kz.iitu.emulator.model.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Service
public class IntegrationService {

    @Value("${service.gateway.url}")
    private String gatewayUrl;

    public Mono<String> getTokenWebClient(String username, String password) {
        WebClient webClient = WebClient.create(this.gatewayUrl);

        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password(password)
                .build();

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/login");
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(loginRequest);

        //defining headers
        headersSpec
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_PLAIN, MediaType.TEXT_PLAIN)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        return headersSpec.exchangeToMono(r -> {
            if (r.statusCode().equals(HttpStatus.OK)) {
                return r.bodyToMono(String.class);
            } else if (r.statusCode().is4xxClientError()) {
                return Mono.just("Authentication Error");
            } else {
                return r.createException().flatMap(Mono::error);
            }
        });
    }

    public Mono<Indicator> sendIndicators(Indicator indicator, String token) {
        WebClient webClient = WebClient.create(this.gatewayUrl);

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.post();
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/indicator/new");
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec
                .header("Authorization", token)
                .bodyValue(indicator);

        return headersSpec.exchangeToMono(r -> {
            if (r.statusCode().equals(HttpStatus.OK)) {
                return r.bodyToMono(Indicator.class);
            } else {
                return r.createException().flatMap(Mono::error);
            }
        });
    }


//    public Indicator sendIndicators(Indicator indicator, String token) {
//
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Indicator> entity = new HttpEntity<>(indicator, headers);
//        headers.add("Authorization", token);
//
//        ResponseEntity<Indicator> result = this.restTemplate.exchange(this.gatewayUrl + "/indicator/new",
//                HttpMethod.POST,
//                entity,
//                Indicator.class);
//
//        return result.hasBody() ? result.getBody() : null;
//    }
}
