package kz.iitu.business.repository;

import kz.iitu.business.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

    @Query(value = "select * from task order by created_at desc OFFSET :#{[0].offset} LIMIT :#{[0].pageSize}")
    Flux<Task> findAllPageable(Pageable pageable);
}
