package org.ust.proyecto.controllers.mappers;

import org.ust.proyecto.model.Venta;
import org.ust.proyecto.persistence.Entities.VentaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    VentaEntity VentatoVentaEntity (Venta venta);
    Venta VentaEntitytoVenta(VentaEntity ventaEntity);

}
