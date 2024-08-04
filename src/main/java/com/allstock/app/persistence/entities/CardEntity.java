package com.allstock.app.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "targeta")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_tarjeta", length = 16, unique = true)
    private String numberCard;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "mes")
    private short mes;
    @Column(name = "year")
    private short year;
}
