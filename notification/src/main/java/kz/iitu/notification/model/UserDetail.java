package kz.iitu.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.iitu.notification.model.enam.CovidStatus;
import kz.iitu.notification.model.enam.RiskStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String firstName;

    @Column(unique = true)
    private String email;

    @Column
    private String lastName;

    @Column
    private String position;

    @Column
    @Enumerated(EnumType.STRING)
    private RiskStatus riskStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private CovidStatus covidStatus;

    @Column
    private String phoneNumber;

    @Column
    private Date dateOfBirth;
    @Column(name = "department_id")
    private Long departmentId;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    @JsonIgnore
    private Department department;
}
