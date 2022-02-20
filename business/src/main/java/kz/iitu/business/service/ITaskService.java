package kz.iitu.business.service;

import kz.iitu.business.model.PageSupport;
import kz.iitu.business.model.Task;
import kz.iitu.business.model.dto.TaskDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


public interface ITaskService {

    Mono<Task> getById(Long id);
    Mono<PageSupport<TaskDTO>> getAll(Map<String, String> params);
    Mono<Task> createOrUpdate(Task task);
}
