package kz.iitu.business.controller;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/user-detail/{userId}")
    public Flux<Indicator> getByUserId(@PathVariable Long userId) {
        return this.indicatorService.getByAllUserId(userId);
    }
}
