package org.ust.proyecto.controllers.mappers;

import org.ust.proyecto.model.Visita;
import org.ust.proyecto.persistence.Entities.VisitaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {
    VisitaEntity VisitatoVisitaEntity (Visita visita);
    Visita VisitaEntitytoVisita (VisitaEntity visitaEntity);
}
