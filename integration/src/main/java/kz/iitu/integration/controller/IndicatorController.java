package kz.iitu.integration.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "get indicators from mobile app")
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void addIndicator(@RequestBody Indicator indicator) {
        this.indicatorService.addIndicator(indicator);
    }

    @ApiOperation(value = "check last indicator again if they're abnormal")
    @RequestMapping(value = "/recheck", method = RequestMethod.POST)
    public Mono<Indicator> recheck(@RequestBody Indicator indicator) {
        return this.indicatorService.recheck(indicator, indicator.getUserId());
    }

    @ApiOperation(value = "check indicators again with emulator")
    @RequestMapping(value = "/emulator/recheck/{userId}", method = RequestMethod.GET)
    public Mono<Indicator> recheckEmu(@RequestBody(required = false) Indicator indicator,
                                      @PathVariable Long userId) {
        return this.indicatorService.recheck(indicator, userId);
    }
}
