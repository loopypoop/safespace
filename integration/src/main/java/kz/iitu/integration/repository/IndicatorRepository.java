package kz.iitu.integration.repository;

import kz.iitu.integration.model.Indicator;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends ReactiveCrudRepository<Indicator, Long> {
}
