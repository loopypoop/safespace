package kz.iitu.notification.repository;

import kz.iitu.notification.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, Long> {

    Mono<Integer> countAllByUserIdAndIsSeen(Long userId, Boolean isSeen);

    @Query(value = "select * from notification n " +
            "where n.user_id = :userId " +
            "order by created_at desc OFFSET :#{[1].offset} LIMIT :#{[1].pageSize} ")
    Flux<Notification> findAllByUserId(Long userId, Pageable pageable);

    @Query(value = "select * from notification n " +
            "where user_id = :userId and n.topic ~* :topic " +
            "order by created_at desc OFFSET :#{[2].offset} LIMIT :#{[2].pageSize} ")
    Flux<Notification> findAllByUserIdAndTopic(Long userId, String topic, PageRequest pageRequest);
}
