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
@RequestMapping("/documentos")
@Tag(name = "DOCUMENTOS", description = "API PARA DOCUMENTOS")
public class DocumentoController {
    
    @Autowired
    DocumentoService servicio;
    
    @GetMapping("/mostrar")
    @Operation(summary = "Mostrar documentos", description = "Muestra todos los documentos registradas")
    public ResponseEntity<?> mostrarDocumentos() {
        List<DocumentoDTO> lista = servicio.obtenerDocumentos();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/status")
    @Operation(summary = "Mostrar status", description = "Busca los status 0 de documentos")
    public ResponseEntity<?> mostrarStatus() {
        List<DocumentoDTO> lista = servicio.obtenerStatus();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/status-uno")
    @Operation(summary = "Mostrar status 1", description = "Busca los status 1 de documentos")
    public ResponseEntity<?> mostrarStatusUno() {
        List<DocumentoDTO> lista = servicio.obtenerStatusUno();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/status-dos")
    @Operation(summary = "Mostrar status 2", description = "Busca los status 2 de documentos")
    public ResponseEntity<?> mostrarStatusDos() {
        List<DocumentoDTO> lista = servicio.obtenerStatusDos();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar documento", description = "Registra una nueva documento")
    public ResponseEntity<?> guardar(@RequestBody DocumentoDTO dto) {
        try {            
            servicio.insertarDocumenctos(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"Registro exitoso\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"" + e.getMessage() + "\"}");
        }
    }
    
    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar status", description = "Actualiza el status de los documentos")
    public ResponseEntity<?> actualizar(@RequestBody DocumentoDTO dto) {
        try {
            System.out.println("dtos: "+dto.getNumEmpleado()+", "+dto.getDocumento()+", "+dto.getStatus());
            servicio.actualizarDocumenctos(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"Actualización exitosa\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"" + e.getMessage() + "\"}");
        }
    }
}