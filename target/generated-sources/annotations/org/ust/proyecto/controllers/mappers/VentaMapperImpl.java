package org.ust.proyecto.controllers.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.ust.proyecto.model.Venta;
import org.ust.proyecto.persistence.Entities.VentaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T12:45:42-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
@Component
public class VentaMapperImpl implements VentaMapper {

    @Override
    public VentaEntity VentatoVentaEntity(Venta venta) {
        if ( venta == null ) {
            return null;
        }

        VentaEntity ventaEntity = new VentaEntity();

        ventaEntity.setFechaCreacion( venta.getFechaCreacion() );
        ventaEntity.setMonto( venta.getMonto() );
        ventaEntity.setVentaId( venta.getVentaId() );

        return ventaEntity;
    }

    @Override
    public Venta VentaEntitytoVenta(VentaEntity ventaEntity) {
        if ( ventaEntity == null ) {
            return null;
        }

        Venta.VentaBuilder venta = Venta.builder();

        venta.fechaCreacion( ventaEntity.getFechaCreacion() );
        venta.monto( ventaEntity.getMonto() );
        venta.ventaId( ventaEntity.getVentaId() );

        return venta.build();
    }
}
