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
@Table(name = "ETAPAS")
@Entity
@NoArgsConstructor
public class EtapaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long etapaId;

    private String nombre;

    @Column( nullable = false)
    private int orden;

}
