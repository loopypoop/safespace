package kz.iitu.business.service;

import kz.iitu.business.model.Indicator;
import reactor.core.publisher.Flux;

public interface IndicatorService {
    Flux<Indicator> getByAllUserDetailId(Long id);
}
