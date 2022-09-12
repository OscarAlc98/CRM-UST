package org.ust.proyecto.controllers.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.ust.proyecto.model.Etapa;
import org.ust.proyecto.persistence.Entities.EtapaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T12:45:43-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
@Component
public class EtapaMapperImpl implements EtapaMapper {

    @Override
    public EtapaEntity EtapaModelotoEtapaEntity(Etapa etapa) {
        if ( etapa == null ) {
            return null;
        }

        EtapaEntity etapaEntity = new EtapaEntity();

        etapaEntity.setEtapaId( etapa.getEtapaId() );
        etapaEntity.setNombre( etapa.getNombre() );
        etapaEntity.setOrden( etapa.getOrden() );

        return etapaEntity;
    }

    @Override
    public Etapa EtapaEntitytoEtapaModelo(EtapaEntity etapa) {
        if ( etapa == null ) {
            return null;
        }

        Etapa.EtapaBuilder etapa1 = Etapa.builder();

        etapa1.etapaId( etapa.getEtapaId() );
        etapa1.nombre( etapa.getNombre() );
        etapa1.orden( etapa.getOrden() );

        return etapa1.build();
    }
}
