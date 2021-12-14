package kz.iitu.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.iitu.business.model.enam.CovidStatus;
import kz.iitu.business.model.enam.RiskStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userDetail")
    private List<Indicator> indicators;

    @Column(name = "department_id")
    private Long departmentId;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    @JsonIgnore
    private Department department;
}
