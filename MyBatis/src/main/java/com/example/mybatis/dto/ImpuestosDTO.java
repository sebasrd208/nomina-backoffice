package com.example.mybatis.dto;

import com.fasterxml.jackson.annotation.*;
import com.example.mybatis.cifrado.*;
import javax.crypto.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImpuestosDTO {
    
    @JsonIgnore
    private String nombre;
    private String ISR;
    private String IMSS;
    private String impuesto;

    // Encriptar los campos sensibles
    public void encryptFields(SecretKey key) {
        try {
            if (ISR != null) ISR = CryptoUtils.encrypt(ISR, key);
            if (IMSS != null) IMSS = CryptoUtils.encrypt(IMSS, key);
            if (impuesto != null) impuesto = CryptoUtils.encrypt(impuesto, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
