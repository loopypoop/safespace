package kz.iitu.business.service.impl;

import kz.iitu.business.model.PageSupport;
import kz.iitu.business.model.Task;
import kz.iitu.business.model.dto.TaskDTO;
import kz.iitu.business.repository.TaskRepository;
import kz.iitu.business.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public Mono<Task> getById(Long id) {
        return this.taskRepository.findById(id);
    }

    @Override
    public Mono<PageSupport<TaskDTO>> getAll(Map<String, String> params) {
        PageRequest pageRequest = createPageRequest(params);
         AtomicReference<Long> size = new AtomicReference<>(0L);
         this.taskRepository.count().subscribe(size::set);
        return this.taskRepository.findAllPageable(pageRequest)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                        .stream()
                        .collect(Collectors.toList()),
                        pageRequest.getPageNumber(), pageRequest.getPageSize(), size.get()
                ));
    }

    @Override
    public Mono<Task> createOrUpdate(Task task) {
        System.out.println("Task" + task.toString());
        return this.taskRepository.save(task);
    }

    private PageRequest createPageRequest(Map<String, String> params) {
        int page = 0;
        int size = 5;
        Sort sort = Sort.by("id");
        if (params.containsKey("page") && params.containsKey("size")) {
            page = Integer.parseInt(params.get("page"));
            size = Integer.parseInt(params.get("size"));
        }

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
