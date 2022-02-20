package kz.iitu.business.repository;

import kz.iitu.business.model.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserDetailRepository extends ReactiveCrudRepository<UserDetail, Long> {
    Mono<UserDetail> getByUserId(Long userId);

    @Query(value = "select count(ud.*) from user_detail ud " +
            "join users u on u.id = ud.user_id " +
            "where u.role = 'User'")
    Mono<Long> countUsers();
    @Query(value = "select ud.* from user_detail ud " +
            "join users u on u.id = ud.user_id " +
            "where u.role = 'User' OFFSET :#{[0].offset} LIMIT :#{[0].pageSize}")
    Flux<UserDetail> findAllUsers(Pageable pageable);
}
