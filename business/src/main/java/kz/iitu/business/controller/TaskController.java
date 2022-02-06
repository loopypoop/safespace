package kz.iitu.business.controller;

import kz.iitu.business.model.Task;
import kz.iitu.business.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final ITaskService taskService;

    @GetMapping("/{id}")
    public Mono<Task> getTaskById(@PathVariable Long id) {
        return this.taskService.getById(id);
    }

    @PostMapping
    public Mono<Task> createOrUpdate(@RequestBody Task task) {
        return this.taskService.createOrUpdate(task);
    }

    @GetMapping("/all")
    public Flux<Task> getAllPageable(@RequestParam Map<String, String> params) {
        return this.taskService.getAll(params);
    }
}
