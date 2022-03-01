package kz.iitu.emulator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
public class Indicator {

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
