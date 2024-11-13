package com.reto.literalura.utils;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseLibros(
        @JsonAlias(value = "results")
        List<DatosLibros> datosLibros
 ){
}
