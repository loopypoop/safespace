package kz.iitu.business.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TaskDTO {

    private Long id;

    private Timestamp createdAt;

    private String employee;

    private String doctor;

    private String complaints;

    private String recommendations;
}
