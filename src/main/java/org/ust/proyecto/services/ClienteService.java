package org.ust.proyecto.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import org.ust.proyecto.controllers.mappers.ClienteMapper;
import org.ust.proyecto.controllers.mappers.ClienteMapperImpl;
import org.ust.proyecto.model.Cliente;
import org.ust.proyecto.persistence.Repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    ClienteMapper mapper = new ClienteMapperImpl();

    public Cliente guardaCliente(Cliente cliente) {
        return mapper.ClienteEntitytoCliente(
                repository.save(mapper.ClientetoClienteEntity(cliente))
        );
    }

    public List<Cliente> obtenClientes(){
        return repository.findAll().stream().map(cliente -> mapper.ClienteEntitytoCliente(cliente)).collect(Collectors.toList());
    }

    public Optional<Cliente> obtenCliente(long idCliente) {
        return repository.findById(idCliente)
                .map(cliente -> Optional.of(mapper.ClienteEntitytoCliente(cliente)))
                .orElse(Optional.empty());
    }

    public void eliminaCliente(long idcliente){
        repository.deleteById(idcliente);
    }

    public Cliente actualizaCliente(Cliente cliente){
        return mapper.ClienteEntitytoCliente(
                repository.save(mapper.ClientetoClienteEntity(cliente))
        );
    }

    public long cuenteClientes(){
        return repository.count();
    }
}

