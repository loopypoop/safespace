package kz.iitu.gateway.repository;

import kz.iitu.gateway.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
