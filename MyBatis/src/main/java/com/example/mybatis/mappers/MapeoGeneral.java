package com.example.mybatis.mappers;

import org.apache.ibatis.annotations.*;
import com.example.mybatis.values.*;
import org.apache.ibatis.mapping.*;
import com.example.mybatis.DTO.*;
import java.util.*;

/**
 *
 * @author sebas
 */
@Mapper
public interface MapeoGeneral {

    //EMPLEADOS
    @Results(
            id = "r_SP_GETEMPLEADO",
            value = {
                @Result(property = "idEmpleado", column = "ID_EMPLEADO", id = true),
                @Result(property = "documento", column = "DOCUMENTO"),
                @Result(property = "apellido", column = "APELLIDO"),
                @Result(property = "nombre", column = "NOMBRE"),
                @Result(property = "seccion", column = "SECCION"),
                @Result(property = "sueldo", column = "SUELDO")
            }
    )
    @Select(GeneralValue.SP_GETEMPLEADO)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(EmpleadoDTO.class)
    public void SP_GETEMPLEADO(Map<String, Object> params);

    @Select(GeneralValue.SP_INSERT_EMPLEADOS)
    @Options(statementType = StatementType.CALLABLE)
    public void SP_INSERT_EMPLEADOS(Map<String, Object> params);

    @Select(GeneralValue.SP_UPDATE_EMPLEADO)
    @Options(statementType = StatementType.CALLABLE)
    public void SP_UPDATE_EMPLEADO(Map<String, Object> params);
    
    @Results(
            id = "r_SP_DEDUCCIONES",
            value = {
                @Result(property = "nombre", column = "NOMBRE", id = true),
                @Result(property = "bruto", column = "SUELDO_BRUTO"),
                @Result(property = "ISR", column = "ISR"),
                @Result(property = "IMSS", column = "IMSS"),
                @Result(property = "fondo", column = "FONDO_AHORRO"),
                @Result(property = "neto", column = "SUELDO_NETO")
            }
    )
    @Select(GeneralValue.SP_DEDUCCIONES)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(DeduccionesDTO.class)
    public void SP_DEDUCCIONES(Map<String, Object> params);
    
    @Results(
            id = "r_SP_IMPUESTOS",
            value = {
                @Result(property = "nombre", column = "NOMBRE", id = true),                
                @Result(property = "ISR", column = "ISR"),
                @Result(property = "IMSS", column = "IMSS"),
                @Result(property = "impuesto", column = "TOTAL_IMPUESTOS")
            }
    )
    @Select(GeneralValue.SP_IMPUESTOS)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(ImpuestosDTO.class)
    public void SP_IMPUESTOS(Map<String, Object> params);
    
    //COMPAÑIA
    
    @Results(
            id = "r_SP_GETCOMPANIA",
            value = {
                @Result(property = "idCompania",   column = "ID_COMPANIA", id = true),
                @Result(property = "nombre",       column = "NOMBRE"),
                @Result(property = "apellido", column = "APELLIDO"),
                @Result(property = "num_empleado", column = "NUM_EMPLEADO"),
                @Result(property = "rfc",          column = "RFC"),
                @Result(property = "compania",     column = "COMPANIA"),
                @Result(property = "nota",         column = "NOTA"),
                @Result(property = "trimestre",         column = "TRIMESTRE")
                    
            }
    )
    @Select(GeneralValue.SP_GETCOMPANIA)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(CompaniaDTO.class)
    public void SP_GETCOMPANIA(Map<String, Object> params);

    @Select(GeneralValue.SP_SETCOMPANIA)
    @Options(statementType = StatementType.CALLABLE)
    public void SP_SETCOMPANIA(Map<String, Object> params);
    
    @Results(
            id = "r_SP_GETNOMBRE",
            value = {
                @Result(property = "idCompania",   column = "ID_COMPANIA", id = true),
                @Result(property = "nombre",       column = "NOMBRE"),
                @Result(property = "apellido",     column = "APELLIDO"),
                @Result(property = "num_empleado", column = "NUM_EMPLEADO"),
                @Result(property = "rfc",          column = "RFC"),
                @Result(property = "compania",     column = "COMPANIA"),
                @Result(property = "nota",         column = "NOTA"),
                @Result(property = "trimestre",         column = "TRIMESTRE")
            }
    )
    @Select(GeneralValue.SP_GETNOMBRE)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(CompaniaDTO.class)
    public void SP_GETNOMBRE(Map<String, Object> params);
    
    //DOCUMENTO
    
    @Results(
            id = "r_SP_GETDOCUMENTOS",
            value = {
                @Result(property = "idDocumento",   column = "ID_DOCUMENTO", id = true),
                @Result(property = "nombre",       column = "NOMBRE"),
                @Result(property = "apellido", column = "APELLIDO"),
                @Result(property = "documento", column = "DOCUMENTO"),
                @Result(property = "correo",          column = "CORREO"),
                @Result(property = "status",     column = "STATUS")
            }
    )
    @Select(GeneralValue.SP_GETDOCUMENTOS)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(DocumentoDTO.class)
    public void SP_GETDOCUMENTOS(Map<String, Object> params);
    
    @Results(
            id = "r_SP_GETSTATUS",
            value = {
                @Result(property = "idDocumento",   column = "ID_DOCUMENTO", id = true),
                @Result(property = "nombre",       column = "NOMBRE"),
                @Result(property = "apellido", column = "APELLIDO"),
                @Result(property = "documento", column = "DOCUMENTO"),
                @Result(property = "correo",          column = "CORREO"),
                @Result(property = "status",     column = "STATUS")
            }
    )
    @Select(GeneralValue.SP_GETSTATUS)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(DocumentoDTO.class)
    public void SP_GETSTATUS(Map<String, Object> params);
    
    @Select(GeneralValue.SP_SETDOCUMENTOS)
    @Options(statementType = StatementType.CALLABLE)
    public void SP_SETDOCUMENTOS(Map<String, Object> params);
    
    @Select(GeneralValue.SP_UPDTDOCUMENTOS)
    @Options(statementType = StatementType.CALLABLE)
    public void SP_UPDTDOCUMENTOS(Map<String, Object> params);
}
