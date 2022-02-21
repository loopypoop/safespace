package kz.iitu.business.service;

import kz.iitu.business.model.Indicator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

public interface IndicatorService {
    Flux<Indicator> getByAllUserId(Long id);
    Flux<Indicator> getAllAvgOfDayByUserId(Long id);
    Flux<Indicator> getAllBySpecificDateAndUserId(Timestamp checkTime, Long id);
    Flux<Indicator> getAllByDate(Timestamp checkDate);
    Mono<Indicator> getLastByUserId(Long userId);
}
