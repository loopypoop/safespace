package kz.iitu.integration.service;

import kz.iitu.integration.model.Indicator;
import reactor.core.publisher.Mono;

public interface IndicatorService {
    Mono<Indicator> addIndicator(Indicator indicator);
}
