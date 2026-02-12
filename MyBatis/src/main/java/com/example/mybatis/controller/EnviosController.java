package com.example.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatis.service.EnviosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;

/**
 *
 * @author sebas
 */
@RestController
@RequestMapping("/envios")
@Tag(name = "API DE ENVIOS", description = "API PARA ENVIOS")
public class EnviosController {
    
    @Autowired
    EnviosService servicio;
        
    @PostMapping("/pendientes")
    @Operation(summary = "Envios pendientes", description = "Envia correos con estatus 0")
    public ResponseEntity<String> enviarCorreosPendientes() {
        try {
            servicio.procesarEnvioCorreos();
            return ResponseEntity.ok("{\"Mensaje\":\"Procesamiento de correos iniciado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar correos: " + e.getMessage());
        }
    }
}
