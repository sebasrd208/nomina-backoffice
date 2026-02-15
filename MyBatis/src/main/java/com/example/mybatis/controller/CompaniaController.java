package com.example.mybatis.controller;

import java.util.*;
import com.example.mybatis.dto.*;
import org.springframework.http.*;
import com.example.mybatis.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("/companias")
@Tag(name = "EMPRESAS", description = "API PARA EMPRESAS")
public class CompaniaController {
        
    @Autowired
    CompaniaService servicio;
    
    @GetMapping("/mostrar")
    @Operation(summary = "Mostrar Empresas", description = "Muestra todos los empresas registradas")
    public ResponseEntity<?> mostrarCompanias() {
        List<CompaniaDTO> lista = servicio.obtenerCompanias();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(lista);
    }
    
    @PostMapping("/guardar")
    @Operation(summary = "Registrar empresa", description = "Registra una nueva empresa")
    public ResponseEntity<?> guardar(@RequestBody CompaniaDTO dto) {
        try {            
            servicio.insertarCompania(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"Registro exitoso\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"" + e.getMessage() + "\"}");
        }
    }
    
    @GetMapping("/empresa/{nombre}")
    @Operation(summary = "Mostrar datos de la empresa", description = "Se obtienen datos de la empresa donde trabaja esta persona")
    public ResponseEntity<?> mostrarEmpresa(@PathVariable String nombre){
        List<CompaniaDTO> usuario = servicio.obtenerNombres(nombre);
        if (usuario==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(usuario);
    }
}
