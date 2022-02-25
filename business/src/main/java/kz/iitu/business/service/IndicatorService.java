package kz.iitu.business.service;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.model.PageSupport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Map;

public interface IndicatorService {
    Flux<Indicator> getByAllUserId(Long id);
    Flux<Indicator> getAllAvgOfDayByUserId(Long id);
    Mono<PageSupport<Indicator>> getAllBySpecificDateAndUserId(Timestamp checkTime,Timestamp checkTimeEnd, Long id, Map<String, String> param);
    Flux<Indicator> getAllByDate(Timestamp checkDate);
    Mono<Indicator> getLastByUserId(Long userId);
}
