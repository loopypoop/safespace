package kz.iitu.business.service;

import kz.iitu.business.model.Task;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


public interface ITaskService {

    Mono<Task> getById(Long id);
    Flux<Task> getAll(Map<String, String> params);
    Mono<Task> createOrUpdate(Task task);
}
