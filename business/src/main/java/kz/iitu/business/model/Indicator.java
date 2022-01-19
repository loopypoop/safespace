package kz.iitu.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("indicators")
public class Indicator {
    @Id
    private Long id;

    private Long userId;

    private Double temperature;

    private Integer heartRate;

    private Integer bloodPressure;

    private Double bloodOxygen;

    private Timestamp checkTime;

    private Date checkDate;

    private Boolean isLast;

    private Integer checkCount;

}
