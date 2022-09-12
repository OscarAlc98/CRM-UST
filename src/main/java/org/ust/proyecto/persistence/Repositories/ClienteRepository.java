package org.ust.proyecto.persistence.Repositories;

import org.ust.proyecto.persistence.Entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long>{
    
}
