package org.ust.proyecto.persistence.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "VISITAS")
@NoArgsConstructor
public class VisitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDateTime fechaProgramada;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String proposito;

    @Column(nullable = false)
    private String vendedor;
}
