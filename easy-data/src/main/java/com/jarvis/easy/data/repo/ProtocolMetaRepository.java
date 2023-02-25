package com.jarvis.easy.data.repo;

import com.jarvis.easy.data.entity.DeviceMetaEntity;
import com.jarvis.easy.data.entity.ProtocolMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaofei
 */
@Repository
public interface ProtocolMetaRepository extends JpaRepository<ProtocolMetaEntity, Long> {
}
