package kz.iitu.business.service;

import kz.iitu.business.model.Indicator;

import java.util.List;

public interface IndicatorService {
    List<Indicator> getByAllUserDetailId(Long id);
}
