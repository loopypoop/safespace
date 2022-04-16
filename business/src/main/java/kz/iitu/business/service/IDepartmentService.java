package kz.iitu.business.service;

import kz.iitu.business.model.Department;
import reactor.core.publisher.Flux;

public interface IDepartmentService {
    Flux<Department> findAll();
}
