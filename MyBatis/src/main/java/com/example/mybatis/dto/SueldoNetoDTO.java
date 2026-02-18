package com.example.mybatis.dto;

import java.util.List;

/**
 *
 * @author sebas
 */
public class SueldoNetoDTO {

    private String nombre;
    private List<CompaniaDTO> datos;
    private List<DeduccionesDTO> deducciones;
    private List<ImpuestosDTO> impuestos;

    public SueldoNetoDTO() {
    }

    public SueldoNetoDTO(String nombre, List<CompaniaDTO> datos, List<DeduccionesDTO> deducciones, List<ImpuestosDTO> impuestos) {
        this.nombre = nombre;
        this.datos = datos;
        this.deducciones = deducciones;
        this.impuestos = impuestos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CompaniaDTO> getDatos() {
        return datos;
    }

    public void setDatos(List<CompaniaDTO> datos) {
        this.datos = datos;
    }        

    public List<DeduccionesDTO> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(List<DeduccionesDTO> deducciones) {
        this.deducciones = deducciones;
    }

    public List<ImpuestosDTO> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<ImpuestosDTO> impuestos) {
        this.impuestos = impuestos;
    }
}
