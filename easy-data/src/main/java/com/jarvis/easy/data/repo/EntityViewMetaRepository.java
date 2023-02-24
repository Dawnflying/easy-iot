package com.jarvis.easy.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lixiaofei
 */
@Repository
public interface EntityViewMetaRepository extends JpaRepository<EntityViewMetaRepository, Long> {
}
