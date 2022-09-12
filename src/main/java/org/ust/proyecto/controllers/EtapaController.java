package org.ust.proyecto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.ust.proyecto.model.Etapa;
import org.ust.proyecto.services.EtapaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/etapa")
@AllArgsConstructor
public class EtapaController {
    private final EtapaService etapaService;

    @GetMapping("/{etapaId}")
    public ResponseEntity<Etapa> getEtapa(@PathVariable Long etapaId){
        Optional<Etapa> etapaDb = etapaService.obtenEtapa(etapaId);

        if (etapaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La etapa especificada no existe.");
        }

        return ResponseEntity.ok(etapaDb.get());
    }

    @GetMapping
    public ResponseEntity<List<Etapa>> getEtapas(){
        return ResponseEntity.ok(etapaService.obtenEtapas());
    }

    @PostMapping
    public ResponseEntity<Void> creaEtapa(@Valid @RequestBody Etapa etapa){
        Etapa nuevaEtapa = etapaService.guardaEtapa(etapa);
        return ResponseEntity.created(URI.create(String.valueOf(nuevaEtapa.getEtapaId()))).build();
    }

    @PutMapping("/{etapaId}")
    public ResponseEntity<Void> actualizaEtapa(@PathVariable Long etapaId, @RequestBody @Valid Etapa etapa){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{etapaId}")
    public ResponseEntity<Void> eliminaEtapa(@PathVariable Long etapaId){
        etapaService.eliminaEtapa(etapaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
