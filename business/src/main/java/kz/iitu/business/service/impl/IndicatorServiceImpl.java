package kz.iitu.business.service.impl;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.model.PageSupport;
import kz.iitu.business.repository.IndicatorRepository;
import kz.iitu.business.service.IndicatorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class IndicatorServiceImpl implements IndicatorService {

    private final IndicatorRepository indicatorRepository;

    public IndicatorServiceImpl(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    @Override
    public Flux<Indicator> getAllAvgOfDayByUserId(Long id) {
        return this.indicatorRepository.getAllAvgOfDayByUserId(id);
    }

    @Override
    public Flux<Indicator> getAllByDate(Timestamp checkDate) {
        return null;
    }

    @Override
    public Mono<PageSupport<Indicator>> getAllBySpecificDateAndUserId(Timestamp checkTime, Timestamp checkTimeEnd, Long id, Map<String, String> param) {
        PageRequest pageRequest = createPageRequest(param);
        AtomicReference<Long> size = new AtomicReference<>(0L);
        this.indicatorRepository.countAllByCheckTimeBetweenAndUserId(checkTime, checkTimeEnd, id)
                .subscribe(size::set);
        return indicatorRepository.findAllByCheckTimeBetweenAndUserIdOrderByCheckTimeDescPageable(checkTime, checkTimeEnd, id, pageRequest)
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                            .stream()
                            .collect(Collectors.toList()),
                        pageRequest.getPageNumber(), pageRequest.getPageSize(), size.get()
                ));
    }

    @Override
    public Flux<Indicator> getByAllUserId(Long userId) {
        return this.indicatorRepository.getAllByUserId(userId);
    }

    @Override
    public Mono<Indicator> getLastByUserId(Long userId) {
        return this.indicatorRepository.getByUserIdAndIsLast(userId, true);
    }

    private PageRequest createPageRequest(Map<String, String> params) {
        int page = 0;
        int size = 5;
        Sort sort = Sort.by("id");
        if (params.containsKey("page") && params.containsKey("size")) {
            page = Integer.parseInt(params.get("page"));
            size = Integer.parseInt(params.get("size"));
        }

        if (params.containsKey("sortBy"))
            sort = Sort.by(params.get("sortBy"));
        if (params.containsKey("sortDirection")) {
            if (params.get("sortDirection").equals("asc")) {
                sort.ascending();
            } else {
                sort.descending();
            }
        }

        return PageRequest.of(page, size, sort);
    }

}
