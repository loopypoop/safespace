package kz.iitu.business.controller;

import kz.iitu.business.model.Department;
import kz.iitu.business.service.IDepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final IDepartmentService service;

    public DepartmentController(IDepartmentService service) {
        this.service = service;
    }

    @GetMapping("/get/all")
    public Flux<Department> getAll() {
        return service.findAll();
    }
}
