package org.ust.proyecto.controllers.mappers;

import org.ust.proyecto.model.Etapa;
import org.ust.proyecto.persistence.Entities.EtapaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtapaMapper {
    EtapaEntity EtapaModelotoEtapaEntity(Etapa etapa);
    Etapa EtapaEntitytoEtapaModelo(EtapaEntity etapa);
}
