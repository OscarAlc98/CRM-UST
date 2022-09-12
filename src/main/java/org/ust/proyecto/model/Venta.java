package org.ust.proyecto.model;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Venta {

    @PositiveOrZero(message = "El identificador de la venta no puede ser un número negativo")
    private long ventaId;

    @DecimalMin(value = "1.00", inclusive = true, message = "La venta debe ser de al menos 1.00")
    private float monto;

    private Producto productos;

    private Cliente cliente;

    @PastOrPresent(message = "La venta no puede ocurrir en el futuro.")
    private LocalDateTime fechaCreacion;

}
