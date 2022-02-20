package kz.iitu.business.repository;

import kz.iitu.business.model.Task;
import kz.iitu.business.model.dto.TaskDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

    @Query(value = "select task.id as id," +
            " created_at," +
            " doc.first_name || ' ' || doc.last_name as doctor," +
            " emp.first_name || ' ' || emp.last_name as employee," +
            " complaints," +
            " recommendations" +
            " from task" +
            " left join user_detail doc on doc.user_id = task.doctor_id" +
            " left join user_detail emp on emp.user_id = task.employee_id" +
            " order by created_at desc OFFSET :#{[0].offset} LIMIT :#{[0].pageSize}")
    Flux<TaskDTO> findAllPageable(Pageable pageable);

}
