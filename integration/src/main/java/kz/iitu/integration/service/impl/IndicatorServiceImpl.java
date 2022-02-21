package kz.iitu.integration.service.impl;

import kz.iitu.integration.model.Indicator;
import kz.iitu.integration.model.Notification;
import kz.iitu.integration.repository.IndicatorRepository;
import kz.iitu.integration.service.IndicatorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class IndicatorServiceImpl implements IndicatorService {

    private final IndicatorRepository indicatorRepository;

    private final RestTemplate restTemplate;

    @Value("service.notification.url")
    private final String notificationUrl;

    @Override
    public Mono<Indicator> addIndicator(Indicator indicator) {
        return this.indicatorRepository.findByUserIdAndIsLast(indicator.getUserId(), true)
                .map(v -> {
                    v.setIsLast(false);
                    this.indicatorRepository.save(v).subscribe(s -> {

                        int count = 0;
                        String message = "";
                        if (s.getTemperature() > 37.5) {
                            count++;
                            message = "Temperature is too high! Please check your indicators again.";
                            System.out.println("Temperature is too high!");
                        }
                        if (s.getBloodOxygen() < 95.0) {
                            count++;
                            message = "Blood oxygen is too low! Please check your indicators again.";
                            System.out.println("Blood oxygen is too low!");
                        }
                        if (s.getUpperBloodPressure() > 140) {
                            count++;
                            message = "Upper blood pressure is too high! Please check your indicators again.";
                            System.out.println("Upper blood pressure is too high!");
                        }
                        if (s.getHeartRate() > 90 || s.getHeartRate() < 55) {
                            count++;
                            message = "Heart rate is too high/low! Please check your indicators again.";
                            System.out.println("Heart rate is too high/low!");
                        }

                        if (count > 1) {
                            message = "Some of your indicators are abnormal. Please check it again!";
                        }

                        if (count != 0) {
                            Notification notification = Notification.builder()
                                    .createdAt(new Timestamp(System.currentTimeMillis()))
                                    .topic("Abnormal indicators")
                                    .content(message)
                                    .isSeen(false)
                                    .userId(indicator.getUserId())
                                    .build();
                            System.out.println(notification.toString());
                            HttpHeaders headers = new HttpHeaders();
                            HttpEntity<Notification> entity = new HttpEntity<>(notification, headers);

                            this.restTemplate.exchange(notificationUrl + "create",
                                    HttpMethod.POST,
                                    entity,
                                    Notification.class);
                        }
                        indicator.setIsLast(true);
                        this.indicatorRepository.save(indicator).subscribe();
                    });
                    indicator.setIsLast(true);
                    return indicator;
                });
    }

    @Override
    public Mono<Indicator> recheck(Indicator indicator) {
        return this.indicatorRepository.findByUserIdAndIsLast(indicator.getUserId(), true)
                .map(v -> {
                    indicator.setId(v.getId());
                    indicator.setIsLast(true);
                    this.indicatorRepository.save(indicator).subscribe(s -> {

                        int count = 0;
                        String message = "";
                        if (s.getTemperature() > 37.5) {
                            count++;
                            message = "Temperature is too high! Please check your indicators again.";
                            System.out.println("Temperature is too high!");
                        }
                        if (s.getBloodOxygen() < 95.0) {
                            count++;
                            message = "Blood oxygen is too low! Please check your indicators again.";
                            System.out.println("Blood oxygen is too low!");
                        }
                        if (s.getUpperBloodPressure() > 140) {
                            count++;
                            message = "Upper blood pressure is too high! Please check your indicators again.";
                            System.out.println("Upper blood pressure is too high!");
                        }
                        if (s.getHeartRate() > 90 || s.getHeartRate() < 55) {
                            count++;
                            message = "Heart rate is too high/low! Please check your indicators again.";
                            System.out.println("Heart rate is too high/low!");
                        }

                        if (count > 1) {
                            message = "Some of your indicators are abnormal. Please check it again!";
                        }

                        if (count != 0) {
                            Notification notification = Notification.builder()
                                    .createdAt(new Timestamp(System.currentTimeMillis()))
                                    .topic("Abnormal indicators")
                                    .content(message)
                                    .isSeen(false)
                                    .userId(indicator.getUserId())
                                    .build();

                            HttpHeaders headers = new HttpHeaders();
                            HttpEntity<Notification> entity = new HttpEntity<>(notification, headers);

                            this.restTemplate.exchange(notificationUrl + "create",
                                    HttpMethod.POST,
                                    entity,
                                    Notification.class);
                        }

                    });
                    indicator.setIsLast(true);
                    return indicator;
                });
    }
}
