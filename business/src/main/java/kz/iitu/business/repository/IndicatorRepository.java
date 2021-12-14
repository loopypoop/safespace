package kz.iitu.business.repository;

import kz.iitu.business.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    Indicator getByUserDetailId(Long userDetailId);
    List<Indicator> getAllByUserDetailId(Long userDetailId);
}
