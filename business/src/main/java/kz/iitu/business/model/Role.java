package kz.iitu.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String roleName;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;
}
