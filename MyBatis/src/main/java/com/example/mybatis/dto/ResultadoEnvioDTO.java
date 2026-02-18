package com.example.mybatis.dto;

import java.util.List;

/**
 *
 * @author sebas
 */
public class ResultadoEnvioDTO {
    private List<String> correosEnviados;
    private List<String> correosFallidos;
    private int totalEnviados;
    private int totalFallidos;
    private long tiempoEjecucionMs;

    public ResultadoEnvioDTO() {
    }

    public ResultadoEnvioDTO(List<String> correosEnviados, List<String> correosFallidos, int totalEnviados, int totalFallidos, long tiempoEjecucionMs) {
        this.correosEnviados = correosEnviados;
        this.correosFallidos = correosFallidos;
        this.totalEnviados = totalEnviados;
        this.totalFallidos = totalFallidos;
        this.tiempoEjecucionMs = tiempoEjecucionMs;
    }

    public List<String> getCorreosEnviados() {
        return correosEnviados;
    }

    public void setCorreosEnviados(List<String> correosEnviados) {
        this.correosEnviados = correosEnviados;
    }

    public List<String> getCorreosFallidos() {
        return correosFallidos;
    }

    public void setCorreosFallidos(List<String> correosFallidos) {
        this.correosFallidos = correosFallidos;
    }

    public int getTotalEnviados() {
        return totalEnviados;
    }

    public void setTotalEnviados(int totalEnviados) {
        this.totalEnviados = totalEnviados;
    }

    public int getTotalFallidos() {
        return totalFallidos;
    }

    public void setTotalFallidos(int totalFallidos) {
        this.totalFallidos = totalFallidos;
    }

    public long getTiempoEjecucionMs() {
        return tiempoEjecucionMs;
    }

    public void setTiempoEjecucionMs(long tiempoEjecucionMs) {
        this.tiempoEjecucionMs = tiempoEjecucionMs;
    }           
}
