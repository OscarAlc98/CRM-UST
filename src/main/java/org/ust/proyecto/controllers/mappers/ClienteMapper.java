package org.ust.proyecto.controllers.mappers;

import org.mapstruct.Mapper;


import org.ust.proyecto.model.Cliente;
import org.ust.proyecto.persistence.Entities.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteEntity ClientetoClienteEntity (Cliente cliente);
    Cliente ClienteEntitytoCliente (ClienteEntity cliente);
}
