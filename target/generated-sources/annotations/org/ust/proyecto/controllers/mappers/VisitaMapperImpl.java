package org.ust.proyecto.controllers.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.ust.proyecto.model.Visita;
import org.ust.proyecto.persistence.Entities.VisitaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T12:45:43-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
@Component
public class VisitaMapperImpl implements VisitaMapper {

    @Override
    public VisitaEntity VisitatoVisitaEntity(Visita visita) {
        if ( visita == null ) {
            return null;
        }

        VisitaEntity visitaEntity = new VisitaEntity();

        visitaEntity.setDireccion( visita.getDireccion() );
        visitaEntity.setFechaProgramada( visita.getFechaProgramada() );
        visitaEntity.setId( visita.getId() );
        visitaEntity.setProposito( visita.getProposito() );
        visitaEntity.setVendedor( visita.getVendedor() );

        return visitaEntity;
    }

    @Override
    public Visita VisitaEntitytoVisita(VisitaEntity visitaEntity) {
        if ( visitaEntity == null ) {
            return null;
        }

        Visita.VisitaBuilder visita = Visita.builder();

        visita.direccion( visitaEntity.getDireccion() );
        visita.fechaProgramada( visitaEntity.getFechaProgramada() );
        visita.id( visitaEntity.getId() );
        visita.proposito( visitaEntity.getProposito() );
        visita.vendedor( visitaEntity.getVendedor() );

        return visita.build();
    }
}
