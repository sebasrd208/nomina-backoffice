package com.example.mybatis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DeduccionesDTO {
    
    @JsonIgnore
    private String nombre;
    private String bruto;
    private String ISR;
    private String IMSS;
    private String fondo;
    private String neto;

    public DeduccionesDTO() {
    }

    public DeduccionesDTO(String nombre, String bruto, String ISR, String IMSS, String fondo, String neto) {
        this.nombre = nombre;
        this.bruto = bruto;
        this.ISR = ISR;
        this.IMSS = IMSS;
        this.fondo = fondo;
        this.neto = neto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getISR() {
        return ISR;
    }

    public void setISR(String ISR) {
        this.ISR = ISR;
    }

    public String getIMSS() {
        return IMSS;
    }

    public void setIMSS(String IMSS) {
        this.IMSS = IMSS;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getNeto() {
        return neto;
    }

    public void setNeto(String neto) {
        this.neto = neto;
    }        
}
