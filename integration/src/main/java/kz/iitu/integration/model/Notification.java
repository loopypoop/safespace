package kz.iitu.integration.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
