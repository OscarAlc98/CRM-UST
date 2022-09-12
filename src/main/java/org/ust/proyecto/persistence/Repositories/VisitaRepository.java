package org.ust.proyecto.persistence.Repositories;

import org.ust.proyecto.persistence.Entities.VisitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaRepository extends JpaRepository<VisitaEntity,Long>{
    
}
