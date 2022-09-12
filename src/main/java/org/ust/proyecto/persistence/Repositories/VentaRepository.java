package org.ust.proyecto.persistence.Repositories;

import org.ust.proyecto.persistence.Entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity,Long>{
    
}
