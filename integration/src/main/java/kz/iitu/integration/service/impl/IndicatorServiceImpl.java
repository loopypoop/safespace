package kz.iitu.integration.service.impl;

import kz.iitu.integration.model.Indicator;
import kz.iitu.integration.repository.IndicatorRepository;
import kz.iitu.integration.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class IndicatorServiceImpl implements IndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Override
    public Mono<Indicator> addIndicator(Indicator indicator) {
        return this.indicatorRepository.findByUserIdAndIsLast(indicator.getUserId(), true)
                .map(v -> {
                    v.setIsLast(false);
                    this.indicatorRepository.save(v).subscribe(s -> {

                        if (s.getTemperature() > 37.5
                            || s.getBloodOxygen() < 95.0
                            || s.getUpperBloodPressure() > 140
                            || s.getUpperBloodPressure() < 100
                            || s.getLowerBloodPressure() > 90
                            || s.getLowerBloodPressure() < 60
                            || s.getHeartRate() > 90
                            || s.getHeartRate() < 55) {

                        }
                        indicator.setIsLast(true);
                        this.indicatorRepository.save(indicator).subscribe();
                    });
                    indicator.setIsLast(true);
                    return indicator;
                });
    }
}
