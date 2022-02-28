package kz.iitu.emulator.model;

import lombok.Getter;
import lombok.Setter;;

import java.sql.Timestamp;

@Getter
@Setter
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
