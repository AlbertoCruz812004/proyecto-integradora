package com.allstock.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allstock.app.persistence.entities.EmpresaEntity;
import com.allstock.app.services.EmpresaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaServiceImpl service;

    @Autowired
    public EmpresaController(EmpresaServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/create-empresa")
    public ResponseEntity<?> save(@RequestBody @Valid EmpresaEntity entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }
}
