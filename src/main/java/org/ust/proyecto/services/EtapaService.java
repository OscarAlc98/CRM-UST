package org.ust.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ust.proyecto.controllers.mappers.EtapaMapper;
import org.ust.proyecto.controllers.mappers.EtapaMapperImpl;
import org.ust.proyecto.model.Etapa;
import org.ust.proyecto.persistence.Repositories.EtapaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtapaService {

    private final EtapaRepository repository;
    EtapaMapper mapper = new EtapaMapperImpl();
    public Etapa guardaEtapa(Etapa etapa) {
        return mapper.EtapaEntitytoEtapaModelo(
                repository.save(mapper.EtapaModelotoEtapaEntity(etapa))
        );
    }

    public List<Etapa> obtenEtapas(){
        return repository.findAll().stream().map(etapa -> mapper.EtapaEntitytoEtapaModelo(etapa)).collect(Collectors.toList());
    }

    public Optional<Etapa> obtenEtapa(long etapaId) {
        return repository.findById(etapaId)
                .map(etapa -> Optional.of(mapper.EtapaEntitytoEtapaModelo(etapa)))
                .orElse(Optional.empty());
    }

    public void eliminaEtapa(long idetapa){
        repository.deleteById(idetapa);
    }

    public Etapa actualizaEtapa(Etapa etapa){
        return mapper.EtapaEntitytoEtapaModelo(
                repository.save(mapper.EtapaModelotoEtapaEntity(etapa))
        );
    }

    public long cuenteEtapa(){
        return repository.count();
    }
}
