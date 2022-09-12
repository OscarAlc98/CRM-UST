package org.ust.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ust.proyecto.controllers.mappers.VentaMapper;
import org.ust.proyecto.controllers.mappers.VentaMapperImpl;
import org.ust.proyecto.model.Venta;
import org.ust.proyecto.persistence.Repositories.VentaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository repository;
    VentaMapper mapper = new VentaMapperImpl();

    public Venta guardaVenta(Venta venta) {
        return mapper.VentaEntitytoVenta(
                repository.save(mapper.VentatoVentaEntity(venta))
        );
    }

    public List<Venta> obtenVentas(){
        return repository.findAll().stream().map(venta -> mapper.VentaEntitytoVenta(venta)).collect(Collectors.toList());
    }

    public Optional<Venta> obtenVenta(long idVenta) {
        return repository.findById(idVenta)
                .map(venta -> Optional.of(mapper.VentaEntitytoVenta(venta)))
                .orElse(Optional.empty());
    }

    public void eliminaVenta(long idventa){
        repository.deleteById(idventa);
    }

    public Venta actualizaVenta(Venta venta){
        return mapper.VentaEntitytoVenta(
                repository.save(mapper.VentatoVentaEntity(venta))
        );
    }

    public long cuenteVentas(){
        return repository.count();
    }

}

