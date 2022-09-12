package org.ust.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ust.proyecto.controllers.mappers.VisitaMapper;
import org.ust.proyecto.controllers.mappers.VisitaMapperImpl;
import org.ust.proyecto.model.Visita;
import org.ust.proyecto.persistence.Repositories.VisitaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitaService {

    private final VisitaRepository repository;
    VisitaMapper mapper = new VisitaMapperImpl();

    public Visita guardaVisita(Visita visita) {
        return mapper.VisitaEntitytoVisita(
                repository.save(mapper.VisitatoVisitaEntity(visita))
        );
    }
    
    public List<Visita> obtenVisitas(){
        return repository.findAll().stream().map(visita -> mapper.VisitaEntitytoVisita(visita)).collect(Collectors.toList());
    }

    public Optional<Visita> obtenVisita(long idVisita) {
        return repository.findById(idVisita)
                .map(visita -> Optional.of(mapper.VisitaEntitytoVisita(visita)))
                .orElse(Optional.empty());
    }

    public void eliminaVisita(long idvisita){
        repository.deleteById(idvisita);
    }

    public Visita actualizaVisita(Visita visita){
        return mapper.VisitaEntitytoVisita(
                repository.save(mapper.VisitatoVisitaEntity(visita))
        );
    }

    public long cuenteVisita(){
        return repository.count();
    }
}

