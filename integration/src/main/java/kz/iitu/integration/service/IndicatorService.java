package kz.iitu.integration.service;

import kz.iitu.integration.model.Indicator;
import reactor.core.publisher.Mono;

public interface IndicatorService {
    void addIndicator(Indicator indicator);

    Mono<Indicator> recheck(Indicator indicator);
}
