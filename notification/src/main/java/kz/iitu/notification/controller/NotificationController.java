package kz.iitu.notification.controller;

import com.google.firebase.messaging.Message;
import kz.iitu.notification.model.DirectNotification;
import kz.iitu.notification.service.impl.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private FCMService fcm;

    @PostMapping("/notification")
    public void sendTargetNotification(@RequestBody DirectNotification notification) {
        fcm.sendNotificationToTarget(notification);
    }

}
