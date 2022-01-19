package kz.iitu.business.repository;

import kz.iitu.business.model.Indicator;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IndicatorRepository extends CrudRepository<Indicator, Long> {
    Flux<Indicator> getAllByUserId(Long userDetailId);
    Mono<Indicator> getByUserIdAndIsLast(Long userId, Boolean isLast);
    @Query(value = "SELECT DATE(check_time) as check_date,  AVG(blood_pressure) AS blood_pressure, AVG(temperature) AS temperature, AVG(heart_rate) AS heart_rate, AVG(blood_oxygen) AS blood_oxygen," +
            "COUNT(*) AS check_count " +
            "FROM indicators" +
            "WHERE user_id = %?1" +
            " GROUP BY DATE(check_time)" +
            " ORDER BY DATE(check_time)")
    Flux<Indicator> getAllAvgOfDayByUserId(Long userDetailId);
}

