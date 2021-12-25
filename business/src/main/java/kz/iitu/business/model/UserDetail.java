package kz.iitu.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.business.model.enam.CovidStatus;
import kz.iitu.business.model.enam.RiskStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

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
