package kz.iitu.business.repository;

import kz.iitu.business.model.Indicator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IndicatorRepository extends CrudRepository<Indicator, Long> {
    Mono<Indicator> getByUserDetailId(Long userDetailId);
    Flux<Indicator> getAllByUserId(Long userDetailId);
}
