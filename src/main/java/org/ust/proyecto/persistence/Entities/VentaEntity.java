package org.ust.proyecto.persistence.Entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "VENTAS")
@NoArgsConstructor
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ventaId;

    private float monto;
    private String producto;
    private int clienteId;
    @PastOrPresent(message = "no se pueden fechas futuras")
    private LocalDateTime fechaCreacion;
}
