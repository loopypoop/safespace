package kz.iitu.business.repository;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.model.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IndicatorRepository extends CrudRepository<Indicator, Long> {
    Flux<Indicator> getAllByUserId(Long userDetailId);
    Mono<Indicator> getByUserIdAndIsLast(Long userId, Boolean isLast);
    @Query(value = "SELECT DATE(check_time)  AS checkTime,  AVG(blood_pressure) AS blood_pressure,\n" +
            "       COUNT(*) AS NumPosts\n" +
            "FROM   indicators\n" +
            "GROUP BY DATE(check_time)\n" +
            "ORDER BY checkTime\n")
    Flux<Indicator> getAllAvgOfDayByUserId(Long userDetailId);
}
