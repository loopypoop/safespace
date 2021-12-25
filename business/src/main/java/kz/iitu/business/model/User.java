package kz.iitu.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private String role;

    @JsonIgnore
    private UserDetail userDetail;

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }
}
