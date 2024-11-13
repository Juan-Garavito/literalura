package com.reto.literalura.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos {

    ObjectMapper mapper;

    public ConvertirDatos() {
        mapper = new ObjectMapper();
    }

    public <T> Object ConvertirAClase(String json, Class clase)  {
        try {
            return mapper.readValue(json, clase);
        }catch (JsonProcessingException e){
            System.out.println("Error al convertir JSON");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
