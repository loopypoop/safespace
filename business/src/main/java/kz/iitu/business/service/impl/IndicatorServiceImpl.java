package kz.iitu.business.service.impl;

import kz.iitu.business.model.Indicator;
import kz.iitu.business.repository.IndicatorRepository;
import kz.iitu.business.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorServiceImpl implements IndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Override
    public List<Indicator> getByAllUserDetailId(Long userDetailId) {
        return this.indicatorRepository.getAllByUserDetailId(userDetailId);
    }
}
