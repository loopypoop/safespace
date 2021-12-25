package kz.iitu.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private Long id;

    private String name;

    private String description;

    private List<UserDetail> userDetails;
}
