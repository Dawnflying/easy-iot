package com.jarvis.easy.data.repo;

import com.jarvis.easy.data.entity.DeviceMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaofei
 */
@Repository
public interface DeviceMetaRepository extends JpaRepository<DeviceMetaEntity, Long> {
}
