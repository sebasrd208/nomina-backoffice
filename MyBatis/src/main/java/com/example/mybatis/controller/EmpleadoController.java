package com.example.mybatis.controller;

import java.util.*;
import com.example.mybatis.dto.*;
import org.springframework.http.*;
import com.example.mybatis.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("/empleados")
@Tag(name = "EMPLEADOS", description = "API PARA EMPLEADOS")
public class EmpleadoController {

    @Autowired
    EmpleadoService servicio;

    @Autowired
    EnvioService servicioE;

    @GetMapping("/mostrar")
    @Operation(summary = "Mostrar Información", description = "Muestra todos los empleados registradas")
    public ResponseEntity<?> mostrar() {
        List<EmpleadoDTO> lista = servicio.obtenerEmpleados();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/neto")
    @Operation(summary = "Mostrar sueldo neto encriptado", description = "Muestra un sueldo neto de un empleado especifico encriptando datos sencibles")
    public ResponseEntity<?> mostrarNeto(@RequestParam String numEmpleado) {
        try {
            SueldoNetoDTO usuario = servicio.obtenerSueldoNetoEncriptado(numEmpleado);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
            }
            return ResponseEntity.ok(usuario);
        }catch(Exception s){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Mensaje\":\"Ocurrio un error: "+s.getMessage()+"\"}");
        }
    }

    @GetMapping("/neto-empleado")
    @Operation(summary = "Mostrar sueldo neto", description = "Muestra un sueldo neto de un empleado especifico")
    public ResponseEntity<?> mostrarNetoEmpleado(@RequestParam String numEmpleado) {
        try {
            SueldoNetoDTO usuario = servicio.obtenerSueldoNeto(numEmpleado);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Mensaje\":\"No hay contenido en la lista\"}");
            }
            return ResponseEntity.ok(usuario);
        }catch(Exception s){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Mensaje\":\"Ocurrio un error: "+s.getMessage()+"\"}");
        }
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar empleado", description = "Registra un nueva empleado")
    public ResponseEntity<?> guardar(@RequestBody EmpleadoDTO dto) {
        try {
            servicio.insertarEmpleados(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"Registro exitoso\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"" + e.getMessage() + "\"}");
        }
    }

    /*@PostMapping("/guardar-json")
    @Operation(summary = "Registrar JSON", description = "Registra el JSON del empleado")
    public ResponseEntity<?> guardarJson(@RequestParam String nombre){
        try {
            servicio.obtenerYGuardar(nombre);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"Registro exitoso\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"" + e.getMessage() + "\"}");
        }
    }*/

    @PutMapping("/actualizar")
    @Operation(summary = "Actualizar empleado", description = "Actualiza un nueva empleado existente")
    public ResponseEntity<?> actualizar(@RequestBody EmpleadoDTO dto) {
        try {
            servicio.actualizarEmpleados(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"Actualización exitoso\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/neto/pdf/{numEmpleado}")
    @Operation(summary = "Mostrar sueldo neto", description = "Muestra un sueldo neto de un empleado especifico")
    public void generarPdf(@PathVariable String numEmpleado, HttpServletResponse response) throws Exception {

        byte[] pdfBytes = servicioE.generatePdfSueldo(numEmpleado);

        if (pdfBytes == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"Recibo_" + numEmpleado + ".pdf\"");
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }

    @PostMapping("/enviar")
    @Operation(summary = "Correo con archivo adjunto", description = "Este prueba envia un correo con un archivo adjunto")
    public ResponseEntity<String> enviarCorreo(@RequestParam String correo, @RequestParam String nombre) {
        try {
            servicioE.envioCorreoAdjunto(correo, nombre);
            return ResponseEntity.ok("Correo enviado correctamente a " + correo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar el correo: " + e.getMessage());
        }
    }

}
