package com.example.mybatis.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    
    private Integer idEmpleado;
    private String telefono;
    private String apellido;
    private String nombre;
    private String seccion;
    private String sueldo;
}
