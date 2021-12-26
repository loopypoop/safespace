package kz.iitu.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("indicators")
public class Indicator {
    @Id
    private Long id;

    private Long userDetailId;

    private Double temperature;

    private Integer heartRate;

    private Integer bloodPressure;

    private Double bloodOxygen;

    private Timestamp checkTime;

    private Boolean isLast;

}

