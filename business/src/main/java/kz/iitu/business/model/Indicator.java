package kz.iitu.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "indicators")
public class Indicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_detail_id")
    private Long userDetailId;

    @ManyToOne
    @JoinColumn(name = "user_detail_id", insertable = false, updatable = false)
    @JsonIgnore
    private UserDetail userDetail;

    @Column
    private Double temperature;

    @Column
    private Integer heartRate;

    @Column
    private Integer bloodPressure;

    @Column
    private Double bloodOxygen;

    @Column
    private Timestamp checkTime;

    @Column
    private Boolean isLast;

}
