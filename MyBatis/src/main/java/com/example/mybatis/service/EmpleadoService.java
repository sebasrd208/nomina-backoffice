package com.example.mybatis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.example.mybatis.dto.EmpleadoDTO;
import com.example.mybatis.dto.SueldoNetoDTO;
import com.example.mybatis.dto.CompaniaDTO;
import com.example.mybatis.dto.DeduccionesDTO;
import com.example.mybatis.dto.ImpuestosDTO;
import com.example.mybatis.mappers.MapeoGeneral;

import javax.crypto.SecretKey;

@Service
public class EmpleadoService {

    @Autowired
    MapeoGeneral mapeo;

    @Autowired
    SecretKey secretKey;

    public SueldoNetoDTO obtenerSueldoNetoEncriptado(String numEmpleado) {
        Map<String, Object> params = new HashMap<>();

        params.put("PA_EMPLEADO", numEmpleado);
        mapeo.SP_GETNUMEMPLEADO(params);
        List<CompaniaDTO> compania = (List<CompaniaDTO>) params.get("rec_cursor");

        for (CompaniaDTO c : compania) {
            c.encryptFields(secretKey);
        }

        params.clear();
        params.put("PA_EMPLEADO", numEmpleado);
        mapeo.SP_DEDUCCIONES(params);
        List<DeduccionesDTO> deducciones = (List<DeduccionesDTO>) params.get("rec_cursor");

        for (DeduccionesDTO c : deducciones) {
            c.encryptFields(secretKey);
        }

        params.clear();
        params.put("PA_EMPLEADO", numEmpleado);
        mapeo.SP_IMPUESTOS(params);
        List<ImpuestosDTO> impuestos = (List<ImpuestosDTO>) params.get("rec_cursor");

        for (ImpuestosDTO c : impuestos) {
            c.encryptFields(secretKey);
        }

        return new SueldoNetoDTO(numEmpleado, compania, deducciones, impuestos);
    }

    public SueldoNetoDTO obtenerSueldoNeto(String numEmpleado) {
        Map<String, Object> params = new HashMap<>();

        params.put("PA_EMPLEADO", numEmpleado);
        mapeo.SP_GETNUMEMPLEADO(params);
        List<CompaniaDTO> compania = (List<CompaniaDTO>) params.get("rec_cursor");

        params.clear();
        params.put("PA_EMPLEADO", numEmpleado);
        mapeo.SP_DEDUCCIONES(params);
        List<DeduccionesDTO> deducciones = (List<DeduccionesDTO>) params.get("rec_cursor");


        params.clear();
        params.put("PA_EMPLEADO", numEmpleado);
        mapeo.SP_IMPUESTOS(params);
        List<ImpuestosDTO> impuestos = (List<ImpuestosDTO>) params.get("rec_cursor");

        return new SueldoNetoDTO(numEmpleado, compania, deducciones, impuestos);
    }

    public List<EmpleadoDTO> obtenerEmpleados() {
        Map<String, Object> params = new HashMap<>();
        mapeo.SP_GETEMPLEADO(params);

        List<EmpleadoDTO> empleados = (List<EmpleadoDTO>) params.get("rec_cursor");

        return empleados;
    }

    public void insertarEmpleados(EmpleadoDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("PA_DOCUMENTO", dto.getDocumento());
        params.put("PA_APELLIDO", dto.getApellido());
        params.put("PA_NOMBRE", dto.getNombre());
        params.put("PA_SECCION", dto.getSeccion());
        params.put("PA_SUELDO", dto.getSueldo());

        try {
            mapeo.SP_INSERT_EMPLEADOS(params);
        } catch (DataAccessException s) {
            throw new RuntimeException(s.getMostSpecificCause().getMessage());
        }
    }

    public void actualizarEmpleados(EmpleadoDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("PA_ID", dto.getIdEmpleado());
        params.put("PA_SECCION", dto.getSeccion());
        params.put("PA_SUELDO", dto.getSueldo());

        try {
            mapeo.SP_UPDATE_EMPLEADO(params);
        } catch (DataAccessException s) {
            throw new RuntimeException(s.getMostSpecificCause().getMessage());
        }
    }

    /*public SueldoNetoDTO obtenerYGuardar(String nombre) {
        try {
            SueldoNetoDTO usuario = obtenerSueldoNeto(nombre);

            if (usuario != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(usuario);
                Map<String, Object> params = new HashMap<>();
                params.put("PA_REGISTROS", json);

                mapeo.SP_SETDOCUMENTO(params);
            }else{
                throw new RuntimeException("El usuario no existe o no tiene información");
            }

            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar JSON:"+e.getMessage(), e);
        }
    }*/
}
