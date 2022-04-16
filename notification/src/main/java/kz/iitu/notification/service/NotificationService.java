package kz.iitu.notification.service;


import kz.iitu.notification.model.Notification;
import kz.iitu.notification.model.PageSupport;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface NotificationService {
    Mono<Integer> getNewNotificationCount(Long userId);

    Mono<PageSupport<Notification>> getAllPageable(Long userId, Map<String, String> map);

    Mono<Notification> getNotification(Long id);

    Mono<Notification> create(Notification notification);
}
