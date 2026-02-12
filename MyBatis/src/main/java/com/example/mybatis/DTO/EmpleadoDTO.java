package com.example.mybatis.DTO;

/**
 *
 * @author sebas
 */

public class EmpleadoDTO {
    
    private Integer idEmpleado;
    private String documento;
    private String apellido;
    private String nombre;
    private String seccion;
    private String sueldo;

    public EmpleadoDTO(Integer idEmpleado, String documento, String apellido, String nombre, String seccion, String sueldo) {
        this.idEmpleado = idEmpleado;
        this.documento = documento;
        this.apellido = apellido;
        this.nombre = nombre;
        this.seccion = seccion;
        this.sueldo = sueldo;
    }

    public EmpleadoDTO() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }
}
