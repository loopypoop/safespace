package kz.iitu.integration.repository;

import kz.iitu.integration.model.Indicator;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IndicatorRepository extends ReactiveCrudRepository<Indicator, Long> {
    Mono<Indicator> findByUserIdAndIsLast(Long userId, Boolean isLast);
    Mono<Boolean> existsByUserId(Long userId);
}
