package com.example.mybatis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompaniaDTO {
    
    @JsonIgnore
    private Integer idCompania;
    private String nombre;
    private String apellido;
    private String num_empleado;
    private String rfc;
    private String compania;
    private String nota;
    private String trimestre;

    public CompaniaDTO() {
    }

    public CompaniaDTO(Integer idCompania, String nombre, String apellido, String num_empleado, String rfc, String compania, String nota, String trimestre) {
        this.idCompania = idCompania;
        this.nombre = nombre;
        this.apellido = apellido;
        this.num_empleado = num_empleado;
        this.rfc = rfc;
        this.compania = compania;
        this.nota = nota;
        this.trimestre = trimestre;
    }

    public Integer getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Integer idCompania) {
        this.idCompania = idCompania;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }        

    public String getNum_empleado() {
        return num_empleado;
    }

    public void setNum_empleado(String num_empleado) {
        this.num_empleado = num_empleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }    
}
