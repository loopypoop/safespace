package kz.iitu.business.controller;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/user-detail/{userDetailId}")
    public Flux<Indicator> getByUserDetailId(@PathVariable Long userDetailId) {
        return this.indicatorService.getByAllUserDetailId(userDetailId);
    }
}
