package kz.iitu.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("notification")
public class Notification {
    @Id
    private Long id;

    private Timestamp createdAt;

    private boolean isSeen;

    private String topic;

    private String content;

    private Long userId;

}
