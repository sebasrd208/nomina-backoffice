package com.example.mybatis.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.example.mybatis.DTO.EmpleadoDTO;
import com.example.mybatis.DTO.SueldoNetoDTO;
import com.example.mybatis.DTO.CompaniaDTO;
import com.example.mybatis.DTO.DeduccionesDTO;
import com.example.mybatis.DTO.ImpuestosDTO;
import com.example.mybatis.mappers.MapeoGeneral;

/**
 *
 * @author sebas
 */
@Service
public class EmpleadoService {

    @Autowired
    MapeoGeneral mapeo;

    public SueldoNetoDTO obtenerSueldoNeto(String nombre) {
        Map<String, Object> params = new HashMap<>();

        params.put("PA_NOMBRE", nombre);
        mapeo.SP_GETNOMBRE(params);
        List<CompaniaDTO> compania = (List<CompaniaDTO>) params.get("rec_cursor");

        params.clear();
        params.put("PA_NOMBRE", nombre);
        mapeo.SP_DEDUCCIONES(params);
        List<DeduccionesDTO> deducciones = (List<DeduccionesDTO>) params.get("rec_cursor");

        params.clear();
        params.put("PA_NOMBRE", nombre);
        mapeo.SP_IMPUESTOS(params);
        List<ImpuestosDTO> impuestos = (List<ImpuestosDTO>) params.get("rec_cursor");

        return new SueldoNetoDTO(nombre, compania, deducciones, impuestos);
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
}
