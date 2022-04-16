package kz.iitu.business.service.impl;

import kz.iitu.business.model.Department;
import kz.iitu.business.repository.DepartmentRepository;
import kz.iitu.business.service.IDepartmentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Department> findAll() {
        return repository.findAll();
    }
}
