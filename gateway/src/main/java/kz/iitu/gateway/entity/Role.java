package kz.iitu.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String roleName;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
