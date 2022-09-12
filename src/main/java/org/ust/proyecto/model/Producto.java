package org.ust.proyecto.model;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Producto {

    @PositiveOrZero(message = "El identificador no puede ser un número negativo")
    private long id;

    @NotEmpty(message = "El nombre del cliente no puede estar vacío")
    @Size(min = 5, max = 30, message = "El nombre del cliente debe tener al menos 5 letras y ser menor a 30")
    private String nombre;

    @DecimalMin(value = "1.00")   
    private float precio;    

    private String categoria;

    @Pattern(regexp = "^(\\d{3}[-]?){2}\\d{4}$",message="El numero de Registro es Invalido")
    private String numeroRegistro;
    
    @PastOrPresent
    private LocalDate fechaCreacion;
}
