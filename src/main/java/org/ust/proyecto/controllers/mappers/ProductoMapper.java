package org.ust.proyecto.controllers.mappers;

import org.ust.proyecto.model.Producto;
import org.ust.proyecto.persistence.Entities.ProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoEntity ProductotoProductoEntity(Producto Producto);
    Producto ProductoEntitytoProducto(ProductoEntity etapa);
}
