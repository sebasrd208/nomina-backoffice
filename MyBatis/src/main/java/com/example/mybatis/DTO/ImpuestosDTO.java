package com.example.mybatis.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author sebas
 */
public class ImpuestosDTO {
    
    @JsonIgnore
    private String nombre;
    private String ISR;
    private String IMSS;
    private String impuesto;

    public ImpuestosDTO() {
    }

    public ImpuestosDTO(String nombre, String ISR, String IMSS, String impuesto) {
        this.nombre = nombre;
        this.ISR = ISR;
        this.IMSS = IMSS;
        this.impuesto = impuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }     
}
