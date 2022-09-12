package org.ust.proyecto.persistence.Repositories;

import org.ust.proyecto.persistence.Entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity,Long>{
    
}
