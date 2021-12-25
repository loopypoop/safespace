package kz.iitu.business.service.impl;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.repository.IndicatorRepository;
import kz.iitu.business.service.IndicatorService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndicatorServiceImpl implements IndicatorService {

    private final IndicatorRepository indicatorRepository;

    public IndicatorServiceImpl(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    @Override
    public Flux<Indicator> getByAllUserDetailId(Long userDetailId) {
        return this.indicatorRepository.getAllByUserDetailId(userDetailId);
    }
}
