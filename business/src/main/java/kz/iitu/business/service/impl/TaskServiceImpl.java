package kz.iitu.business.service.impl;

import kz.iitu.business.model.Task;
import kz.iitu.business.repository.TaskRepository;
import kz.iitu.business.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public Mono<Task> getById(Long id) {
        return this.taskRepository.findById(id);
    }

    @Override
    public Flux<Task> getAll(Map<String, String> params) {
        PageRequest pageRequest = createPageRequest(params);
        return this.taskRepository.findAllPageable(pageRequest);
    }

    @Override
    public Mono<Task> createOrUpdate(Task task) {
        System.out.println("Task" + task.toString());
        return this.taskRepository.save(task);
    }

    private PageRequest createPageRequest(Map<String, String> params) {
        int page;
        int size;
        Sort sort = Sort.by("id");
        page = Integer.parseInt(params.get("page"));
        size = Integer.parseInt(params.get("size"));
        if (params.containsKey("sortBy"))
            sort = Sort.by(params.get("sortBy"));
        if (params.containsKey("sortDirection")) {
            if (params.get("sortDirection").equals("asc")) {
                sort.ascending();
            } else {
                sort.descending();
            }
        }

        return PageRequest.of(page, size, sort);
    }
}
