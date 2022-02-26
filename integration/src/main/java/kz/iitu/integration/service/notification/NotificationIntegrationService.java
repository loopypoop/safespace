package kz.iitu.integration.service.notification;

import kz.iitu.integration.model.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationIntegrationService {

    @Value("${service.notification.url}")
    private String notificationUrl;

    private final RestTemplate restTemplate;

    public NotificationIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createNotification(Notification notification) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Notification> entity = new HttpEntity<>(notification, headers);

        this.restTemplate.exchange(notificationUrl + "create",
                HttpMethod.POST,
                entity,
                Notification.class);
    }
}
