package kz.iitu.business.service;

import kz.iitu.business.model.Indicator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IndicatorService {
    Flux<Indicator> getByAllUserId(Long id);
    Flux<Indicator> getAllAvgOfDayByUserId(Long id);
    Mono<Indicator> getLastByUserId(Long userId);
}
