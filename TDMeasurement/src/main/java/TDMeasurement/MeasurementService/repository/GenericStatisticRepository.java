package TDMeasurement.MeasurementService.repository;

import TDMeasurement.MeasurementService.entity.GenericStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface GenericStatisticRepository extends JpaRepository<GenericStatistic, Long> {
        GenericStatistic findByGenericStatisticID(long genericStatisticID);
    }

