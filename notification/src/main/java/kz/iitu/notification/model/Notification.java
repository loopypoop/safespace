package kz.iitu.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private Timestamp createdAt;

    @Column
    private boolean isSeen;

    @Column
    private String content;

    @Column(name = "user_detail_id")
    private Long userDetailId;

    @ManyToOne
    @JoinColumn(name = "user_detail_id", insertable = false, updatable = false)
    @JsonIgnore
    private UserDetail userDetail;

}
