package com.example.mybatis.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
    
    private Integer idDocumento;    
    private String nombre;
    private String apellido;
    private String documento;
    private String numEmpleado;
    private String correo;
    private String status;

}
