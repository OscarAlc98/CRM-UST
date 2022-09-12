package org.ust.proyecto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.ust.proyecto.model.Visita;
import org.ust.proyecto.services.VisitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/visita")
@AllArgsConstructor
public class VisitaController {
    private final VisitaService visitaService;

    @GetMapping("/{visitaId}")
    public ResponseEntity<Visita> getVisita(@PathVariable Long visitaId){
        Optional<Visita> visitaDb = visitaService.obtenVisita(visitaId);

        if (visitaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El visita especificado no existe.");
        }

        return ResponseEntity.ok(visitaDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Visita>> getVisitas(){
        return ResponseEntity.ok(visitaService.obtenVisitas());
    }

    @PostMapping
    public ResponseEntity<Void> creaVisita(@Valid @RequestBody Visita visita){
        Visita visitaNuevo = visitaService.guardaVisita(visita);

        return ResponseEntity.created(URI.create(String.valueOf(visitaNuevo.getId()))).build();
    }

    @PutMapping("/{visitaId}")
    public ResponseEntity<Void> actualizaVisita(@PathVariable Long visitaId, @Valid @RequestBody Visita visita){

        visitaService.actualizaVisita(visita);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{visitaId}")
    public ResponseEntity<Void> eliminaVisita(@PathVariable Long visitaId){

        visitaService.eliminaVisita(visitaId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}