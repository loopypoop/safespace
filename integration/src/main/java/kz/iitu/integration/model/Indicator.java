package kz.iitu.integration.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table("indicators")
public class Indicator {
    @Id
    private Long id;

    private Long userId;

    private Double temperature;

    private Integer heartRate;

    private Integer upperBloodPressure;

    private Integer lowerBloodPressure;

    private Double bloodOxygen;

    private Timestamp checkTime;

    private Boolean isLast;

}

