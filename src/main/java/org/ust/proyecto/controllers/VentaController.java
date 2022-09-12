package org.ust.proyecto.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.ust.proyecto.model.Venta;
import org.ust.proyecto.services.VentaService;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {


    private final VentaService ventaService;

    @GetMapping("/{ventaId}")
    public ResponseEntity<Venta> getVenta(@PathVariable Long ventaId){
        Optional<Venta> ventaDb = ventaService.obtenVenta(ventaId);

        if (ventaDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La venta especificada no existe.");
        }

        return ResponseEntity.ok(ventaDb.get());
    }

    @GetMapping
    public ResponseEntity <List<Venta>> getVentas(@Valid @RequestParam Long ventaId){
        return ResponseEntity.ok(ventaService.obtenVentas());
    }

    @PostMapping
    public ResponseEntity<Void> creaVenta(@Valid @RequestBody Venta venta){
        Venta ventaNueva = ventaService.guardaVenta(venta);

        return ResponseEntity.created(URI.create(String.valueOf(ventaNueva.getVentaId()))).build();
    }

    @PutMapping("/{ventaId}")
    public ResponseEntity<Void> actualizaVenta(@PathVariable Long ventaId, @Valid @RequestBody Venta venta){
        ventaService.actualizaVenta(venta);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{ventaId}")
    public ResponseEntity<Void> eliminaVenta(@PathVariable Long ventaId){
        ventaService.eliminaVenta(ventaId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
