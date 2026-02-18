package com.example.mybatis.dto;

/**
 *
 * @author sebas
 */

public class DocumentoDTO {
    
    private Integer idDocumento;    
    private String nombre;
    private String apellido;
    private String documento;
    private String correo;
    private String status;

    public DocumentoDTO() {
    }

    public DocumentoDTO(Integer idDocumento, String nombre, String apellido, String documento, String correo, String status) {
        this.idDocumento = idDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.correo = correo;        
        this.status = status;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }        
}
