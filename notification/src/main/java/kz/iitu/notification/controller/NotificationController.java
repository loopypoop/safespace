package kz.iitu.notification.controller;

import kz.iitu.notification.model.Notification;
import kz.iitu.notification.model.PageSupport;
import kz.iitu.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/new/byUserId/{userId}")
    public Mono<Integer> getNewNotificationAmount(@PathVariable Long userId) {
        return this.notificationService.getNewNotificationCount(userId);
    }

    @PostMapping("/create")
    public Mono<Notification> create(@RequestBody Notification notification) {
        return this.notificationService.create(notification);
    }

    @GetMapping("/get/all/{userId}")
    public Mono<PageSupport<Notification>> getAllPageable(@PathVariable Long userId, @RequestParam Map<String, String> map) {
        return this.notificationService.getAllPageable(userId, map);
    }

    @GetMapping("/get/{id}")
    public Mono<Notification> getNotification(@PathVariable Long id) {
        return this.notificationService.getNotification(id);
    }
}
