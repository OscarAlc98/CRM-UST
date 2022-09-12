package org.ust.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ust.proyecto.controllers.mappers.ProductoMapper;
import org.ust.proyecto.controllers.mappers.ProductoMapperImpl;
import org.ust.proyecto.model.Producto;
import org.ust.proyecto.persistence.Repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;
    ProductoMapper mapper = new ProductoMapperImpl();

    public Producto guardaProducto(Producto producto) {
        return mapper.ProductoEntitytoProducto(
                repository.save(mapper.ProductotoProductoEntity(producto))
        );
    }

    public List<Producto> obtenProductos(){
        return repository.findAll().stream().map(producto  -> mapper.ProductoEntitytoProducto(producto)).collect(Collectors.toList());
    }

    public Optional<Producto> obtenProducto(long idProducto) {
        return repository.findById(idProducto)
                .map(producto -> Optional.of(mapper.ProductoEntitytoProducto(producto)))
                .orElse(Optional.empty());
    }

    public void eliminaProducto(long idcliente){
        repository.deleteById(idcliente);
    }

    public Producto actualizaProducto(Producto  producto){
        return mapper.ProductoEntitytoProducto(
                repository.save(mapper.ProductotoProductoEntity(producto))
        );
    }

    public long cuenteProducto(){
        return repository.count();
    }

}
