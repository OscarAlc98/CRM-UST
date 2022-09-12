package org.ust.proyecto.persistence.Entities;

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
@Table(name = "CLIENTES")
@NoArgsConstructor
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correo_contacto", nullable = false)
    private String correoContacto;

    @Column(name = "numero_empleado")
    private int numeroEmpleados;

    private String direccion;
}
