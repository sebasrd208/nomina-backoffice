package com.example.mybatis.controller;

import com.example.mybatis.dto.DocumentoDTO;
import com.example.mybatis.dto.ResultadoEnvioDTO;
import com.example.mybatis.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatis.service.EnviosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> enviarCorreosPendientes(){
        
        
        ResultadoEnvioDTO resultado = servicio.procesoEnvioCorreos();

        return ResponseEntity.ok(resultado);
        /*try{
            List<String> correosEnviados = servicio.envioCorreos();

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Proceso finalizado");
            response.put("cantidadEnviados", correosEnviados.size());
            response.put("correosEnviados", correosEnviados);

            return ResponseEntity.ok(response);
            
        }catch(Exception s){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al procesar correos: " + s.getMessage());
        }*/
    }
    
    /*public ResponseEntity<String> enviarCorreosPendientes() {
        try {      
            servicio.procesarEnvioCorreos();
            return ResponseEntity.ok("{\"Mensaje\":\"Envio de correo exitoso a \"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar correos: " + e.getMessage());
        }
    }*/
    
    
}
