package kz.iitu.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userDetail")
    private List<Indicator> indicators;
}
