package kz.iitu.notification.model;

import lombok.Data;

@Data
public class DirectNotification {
    private String target;
    private String title;
    private String message;

}
