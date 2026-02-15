package com.example.mybatis.controller;

import com.example.mybatis.dto.ResultadoEnvioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatis.service.EnviosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/envios")
@Tag(name = "API DE ENVIOS", description = "API PARA ENVIOS")
public class EnviosController {
    
    @Autowired
    EnviosService servicio;
               
    @PostMapping("/pendientes")
    @Operation(summary = "Envios pendientes", description = "Envia correos con estatus 0")
    public ResponseEntity<?> enviarCorreosPendientes(){
        
        ResultadoEnvioDTO resultado = servicio.procesoEnvioCorreos();

        return ResponseEntity.ok(resultado);
    }
}
