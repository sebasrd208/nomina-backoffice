package com.example.mybatis.values;

/**
 *
 * @author sebas
 */
public class GeneralValue {

    //EMPLEADO
    
    public static final String SP_GETEMPLEADO
            = "{ call PA_EMPLEADOS.SP_GETEMPLEADO ("
            + "  #{rec_cursor, mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_GETEMPLEADO}"
            + ") "
            + "}";
        
    public static final String SP_INSERT_EMPLEADOS
            = "{ call PA_EMPLEADOS.SP_INSERT_EMPLEADOS ("
            + "  #{PA_DOCUMENTO, mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_APELLIDO,  mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_NOMBRE,    mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_SECCION,   mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_SUELDO,    mode=IN, jdbcType=VARCHAR}"
            + ")"
            + "}";
        
    public static final String SP_UPDATE_EMPLEADO
            = "{ call PA_EMPLEADOS.SP_UPDATE_EMPLEADO ("
            + "  #{PA_ID,        mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_SECCION,   mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_SUELDO,    mode=IN, jdbcType=VARCHAR}"
            + ")"
            + "}";

    public static final String SP_DEDUCCIONES
            = "{ call PA_EMPLEADOS.SP_DEDUCCIONES ("
            + "  #{rec_cursor, mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_DEDUCCIONES},"
            + "  #{PA_NOMBRE,  mode=IN, jdbcType=VARCHAR}"
            + ") "
            + "}";
    
    public static final String SP_IMPUESTOS
            = "{ call PA_EMPLEADOS.SP_IMPUESTOS ("
            + "  #{rec_cursor,   mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_IMPUESTOS},"
            + "  #{PA_NOMBRE,    mode=IN, jdbcType=VARCHAR}"
            + ") "
            + "}";
    
    //COMPAÑIA
    
    public static final String SP_GETCOMPANIA
            = "{ call PA_COMPANIA.SP_GETCOMPANIA ("
            + "  #{rec_cursor, mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_GETCOMPANIA}"
            + ") "
            + "}";
        
    public static final String SP_SETCOMPANIA
            = "{ call PA_COMPANIA.SP_SETCOMPANIA ("
            + "  #{PA_EMPLEADO,    mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_NOMBRE,      mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_APELLIDO,    mode=IN, jdbcType=VARCHAR},"            
            + "  #{PA_RFC,         mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_EMPRESA,     mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_NOTA,        mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_TRIMESTRE,   mode=IN, jdbcType=VARCHAR}"
            + ")"
            + "}";
    
    public static final String SP_GETNOMBRE
            = "{ call PA_COMPANIA.SP_GETNOMBRE ("
            + "  #{rec_cursor,   mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_GETNOMBRE},"
            + "  #{PA_NOMBRE,    mode=IN, jdbcType=VARCHAR}"            
            + ")"
            + "}";    
    
    //DOCUMENTOS    
    public static final String SP_GETDOCUMENTOS
            = "{ call PA_DOCUMENTO.SP_GETDOCUMENTOS ("
            + "  #{rec_cursor, mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_GETDOCUMENTOS}"
            + ") "
            + "}";
    
    public static final String SP_GETSTATUS
            = "{ call PA_DOCUMENTO.SP_GETSTATUS ("
            + "  #{rec_cursor, mode=OUT, jdbcType=CURSOR, javaType=ResultSet, resultMap=r_SP_GETSTATUS}"           
            + ")"
            + "}";
    
    public static final String SP_SETDOCUMENTOS
            = "{ call PA_DOCUMENTO.SP_SETDOCUMENTOS ("
            + "  #{PA_NOMBRE,    mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_APELLIDO,  mode=IN, jdbcType=VARCHAR},"            
            + "  #{PA_CORREO,    mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_STATUS,    mode=IN, jdbcType=VARCHAR}"
            + ")"
            + "}";
    
    public static final String SP_UPDTDOCUMENTOS
            = "{ call PA_DOCUMENTO.SP_UPDTDOCUMENTOS ("
            + "  #{PA_NOMBRE,    mode=IN, jdbcType=VARCHAR}," 
            + "  #{PA_DOCUMENT,  mode=IN, jdbcType=VARCHAR},"
            + "  #{PA_STATUS,    mode=IN, jdbcType=VARCHAR}"
            + ")"
            + "}";
}
