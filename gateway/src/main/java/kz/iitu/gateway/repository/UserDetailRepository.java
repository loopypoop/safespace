package kz.iitu.gateway.repository;

import kz.iitu.gateway.entity.UserDetail;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserDetailRepository extends ReactiveCrudRepository<UserDetail, Long> {
}
