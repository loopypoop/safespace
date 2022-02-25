package kz.iitu.business.repository;

import kz.iitu.business.model.Indicator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

@Repository
public interface IndicatorRepository extends CrudRepository<Indicator, Long> {
    Flux<Indicator> getAllByUserId(Long userDetailId);

    @Query(value = "select * from indicators where check_time between :checkTimeStart and :checkTimeEnd and user_id = :userId " +
            "order by check_time asc OFFSET :#{[3].offset} LIMIT :#{[3].pageSize}")
    Flux<Indicator> findAllByCheckTimeBetweenAndUserIdOrderByCheckTimeDescPageable(
            Timestamp checkTimeStart, Timestamp checkTimeEnd, Long userId, Pageable pageable
    );

    Mono<Long> countAllByCheckTimeBetweenAndUserIdOrderByCheckTimeDesc(Timestamp checkTimeStart, Timestamp checkTimeEnd, Long userId);
    Mono<Indicator> getByUserIdAndIsLast(Long userId, Boolean isLast);
    @Query(value = "SELECT DATE(check_time) as check_date,  AVG(upper_blood_pressure) AS blood_pressure, AVG(temperature) AS temperature, AVG(heart_rate) AS heart_rate, AVG(blood_oxygen) AS blood_oxygen," +
            "COUNT(*) AS check_count " +
            "FROM indicators" +
            "WHERE user_id = %?1" +
            " GROUP BY DATE(check_time)" +
            " ORDER BY DATE(check_time)")
    Flux<Indicator> getAllAvgOfDayByUserId(Long userDetailId);
}

