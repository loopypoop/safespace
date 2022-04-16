package kz.iitu.gateway.entity;

import kz.iitu.gateway.entity.enam.CovidStatus;
import kz.iitu.gateway.entity.enam.RiskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
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
