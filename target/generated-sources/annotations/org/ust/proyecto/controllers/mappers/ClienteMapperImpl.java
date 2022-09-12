package org.ust.proyecto.controllers.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.ust.proyecto.model.Cliente;
import org.ust.proyecto.persistence.Entities.ClienteEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-12T12:45:43-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.200.v20220719-0747, environment: Java 17.0.4 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity ClientetoClienteEntity(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setCorreoContacto( cliente.getCorreoContacto() );
        clienteEntity.setDireccion( cliente.getDireccion() );
        clienteEntity.setId( cliente.getId() );
        clienteEntity.setNombre( cliente.getNombre() );
        if ( cliente.getNumeroEmpleados() != null ) {
            clienteEntity.setNumeroEmpleados( Integer.parseInt( cliente.getNumeroEmpleados() ) );
        }

        return clienteEntity;
    }

    @Override
    public Cliente ClienteEntitytoCliente(ClienteEntity cliente) {
        if ( cliente == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente1 = Cliente.builder();

        cliente1.correoContacto( cliente.getCorreoContacto() );
        cliente1.direccion( cliente.getDireccion() );
        if ( cliente.getId() != null ) {
            cliente1.id( cliente.getId() );
        }
        cliente1.nombre( cliente.getNombre() );
        cliente1.numeroEmpleados( String.valueOf( cliente.getNumeroEmpleados() ) );

        return cliente1.build();
    }
}
