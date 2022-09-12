package org.ust.proyecto.persistence.Entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PRODUCTOS")
@NoArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 30)
    private String nombre;
    private float precio;  
    private String categoria;
    @Column(name = "Numero_Registro")
    private String numeroRegistro;
    private LocalDate fechaCreacion;
}
