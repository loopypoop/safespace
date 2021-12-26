package kz.iitu.integration.controller;

import kz.iitu.integration.model.Indicator;
import kz.iitu.integration.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    @RequestMapping(method = RequestMethod.POST)
    public Mono<Indicator> addIndicator(@RequestBody Indicator indicator) {
        return this.indicatorService.addIndicator(indicator);
    }
}
