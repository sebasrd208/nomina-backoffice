package com.example.mybatis.controller;

import com.example.mybatis.dto.*;
import org.springframework.http.*;
import com.example.mybatis.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("/envios-num")
@Tag(name = "API DE ENVIOS", description = "API PARA ENVIOS")
public class EnvioController {

    @Autowired
    EnvioService servicio;

    @PostMapping("/pendientes")
    @Operation(summary = "Envios pendientes", description = "Envia correos con estatus 0")
    public ResponseEntity<?> enviarCorreosPendientes(){

        ResultadoEnvioDTO resultado = servicio.procesoEnvioCorreos();

        return ResponseEntity.ok(resultado);
    }
}
