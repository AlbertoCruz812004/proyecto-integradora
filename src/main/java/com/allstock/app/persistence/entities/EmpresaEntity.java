package com.allstock.app.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "Empresa")
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "curp", length = 18)
    private final String CURP;

    @Column(name = "rfc", length = 20)
    private final String RFC;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "pais", length = 45)
    private String pais;

    @Column(name = "ciudad", length = 45)
    private String ciudad;

}
