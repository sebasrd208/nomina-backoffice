package com.example.mybatis.dto;

import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoEnvioDTO {
    private List<String> correosEnviados;
    private List<String> correosFallidos;
    private int totalEnviados;
    private int totalFallidos;
    private long tiempoEjecucionMs;
}
