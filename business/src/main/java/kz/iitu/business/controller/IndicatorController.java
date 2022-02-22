package kz.iitu.business.controller;

import io.swagger.annotations.ApiOperation;
import kz.iitu.business.model.Indicator;
import kz.iitu.business.model.PageSupport;
import kz.iitu.business.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    @ApiOperation(value = "get all user's indicators by its id")
    @GetMapping("/user/all/{userId}")
    public Flux<Indicator> getByAllUserId(@PathVariable Long userId) {
        return this.indicatorService.getByAllUserId(userId);
    }

    @ApiOperation(value = "get all user's average indicators in one day by its id")
    @GetMapping("/user/all/avg/{userId}")
    public Flux<Indicator> getAllAvgOfDayByUserId(@PathVariable Long userId) {
        return this.indicatorService.getAllAvgOfDayByUserId(userId);
    }

    @ApiOperation(value = "get user's indicators of Specific Date")
    @GetMapping("/user/date/userId/{date}/{userId}")
    public Mono<PageSupport<Indicator>> getAllBySpecificDateAndUserId(@PathVariable Long date,
                                                                      @PathVariable Long userId, @RequestParam Map<String, String> param) {
        Timestamp timeDate = new Timestamp(date);
        return this.indicatorService.getAllBySpecificDateAndUserId(timeDate, userId, param);
    }

    @ApiOperation(value = "get user's last indicators by user's id")
    @GetMapping("/user/last/{userId}")
    public Mono<Indicator> getLastByUserId(@PathVariable Long userId) {
        return this.indicatorService.getLastByUserId(userId);
    }

}
