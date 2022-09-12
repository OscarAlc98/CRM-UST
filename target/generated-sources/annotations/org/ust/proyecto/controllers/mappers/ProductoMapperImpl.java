package org.ust.proyecto.controllers.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.ust.proyecto.model.Producto;
import org.ust.proyecto.persistence.Entities.ProductoEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T12:45:43-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoEntity ProductotoProductoEntity(Producto Producto) {
        if ( Producto == null ) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();

        productoEntity.setCategoria( Producto.getCategoria() );
        productoEntity.setFechaCreacion( Producto.getFechaCreacion() );
        productoEntity.setId( Producto.getId() );
        productoEntity.setNombre( Producto.getNombre() );
        productoEntity.setNumeroRegistro( Producto.getNumeroRegistro() );
        productoEntity.setPrecio( Producto.getPrecio() );

        return productoEntity;
    }

    @Override
    public Producto ProductoEntitytoProducto(ProductoEntity etapa) {
        if ( etapa == null ) {
            return null;
        }

        Producto.ProductoBuilder producto = Producto.builder();

        producto.categoria( etapa.getCategoria() );
        producto.fechaCreacion( etapa.getFechaCreacion() );
        producto.id( etapa.getId() );
        producto.nombre( etapa.getNombre() );
        producto.numeroRegistro( etapa.getNumeroRegistro() );
        producto.precio( etapa.getPrecio() );

        return producto.build();
    }
}
