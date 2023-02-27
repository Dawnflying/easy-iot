package com.jarvis.easy.data.repo;

import com.jarvis.easy.data.timeseries.TimeSeriesDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaofei
 */
@Repository
public interface TimeSeriesDataRepository extends JpaRepository<TimeSeriesDataEntity, Long> {
}
