package kz.iitu.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.iitu.notification.model.enam.CovidStatus;
import kz.iitu.notification.model.enam.RiskStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("user_detail")
@Data
public class UserDetail {
    @Id
    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    private String position;

    private RiskStatus riskStatus;

    private CovidStatus covidStatus;

    private String phoneNumber;

    private Date dateOfBirth;

    private Long userId;

    private Long departmentId;

}
