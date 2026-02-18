package com.example.mybatis.service;

import com.example.mybatis.dto.DocumentoDTO;
import java.util.*;
import org.springframework.dao.*;
import com.example.mybatis.mappers.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

/**
 *
 * @author sebas
 */

@Service
public class DocumentoService {
 
    @Autowired
    MapeoGeneral mapeo;
    
    public List<DocumentoDTO> obtenerDocumentos() {
        Map<String, Object> params = new HashMap<>();
        mapeo.SP_GETDOCUMENTOS(params);

        List<DocumentoDTO> documentos = (List<DocumentoDTO>) params.get("rec_cursor");

        return documentos;
    }
    
    public List<DocumentoDTO> obtenerStatus() {
        Map<String, Object> params = new HashMap<>();        
        mapeo.SP_GETSTATUS(params);

        List<DocumentoDTO> documentos = (List<DocumentoDTO>) params.get("rec_cursor");

        return documentos;
    }
    
    public void insertarDocumenctos(DocumentoDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("PA_NOMBRE", dto.getNombre());
        params.put("PA_APELLIDO", dto.getApellido());
        params.put("PA_CORREO", dto.getCorreo());
        params.put("PA_STATUS", dto.getStatus());

        try {
            mapeo.SP_SETDOCUMENTOS(params);
        } catch (DataAccessException s) {
            throw new RuntimeException(s.getMostSpecificCause().getMessage());
        }
    }
    
    public void actualizarDocumenctos(DocumentoDTO dto) {
        Map<String, Object> params = new HashMap<>();        
        params.put("PA_NOMBRE", dto.getNombre());
        params.put("PA_DOCUMENT", dto.getDocumento());
        params.put("PA_STATUS", dto.getStatus());

        try {
            mapeo.SP_UPDTDOCUMENTOS(params);
        } catch (DataAccessException s) {
            throw new RuntimeException(s.getMostSpecificCause().getMessage());
        }
    }
}
