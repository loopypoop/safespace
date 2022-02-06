package kz.iitu.business.model;

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
@Table("task")
public class Task {

    @Id
    private Long id;

    private Timestamp created_at;

    private Long employee_id;

    private Long doctor_id;

    private String complaints;

    private String recommendations;
}
//test bot
