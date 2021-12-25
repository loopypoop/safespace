package kz.iitu.business.repository;

import kz.iitu.business.model.UserDetail;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserDetailRepository extends ReactiveCrudRepository<UserDetail, Long> {
    Mono<UserDetail> getByUserId(Long userId);
}
