package kz.iitu.integration.model;

import kz.iitu.integration.model.enam.CovidStatus;
import kz.iitu.integration.model.enam.RiskStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Getter
@Setter
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

    private Timestamp dateOfBirth;

    private Long userId;

    private Long departmentId;
}
