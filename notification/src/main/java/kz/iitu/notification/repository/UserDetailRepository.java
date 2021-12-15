package kz.iitu.notification.repository;

import kz.iitu.notification.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    UserDetail getByUserId(Long userId);
}
