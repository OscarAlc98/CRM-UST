package org.ust.proyecto.persistence.Repositories;

import org.ust.proyecto.persistence.Entities.EtapaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtapaRepository extends JpaRepository<EtapaEntity,Long> {
    
}
