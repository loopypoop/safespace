package kz.iitu.business.repository;
import kz.iitu.business.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> getAllByRole(String roles, Pageable pageable);
}
