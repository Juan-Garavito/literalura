package com.reto.literalura.utils;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(
        @JsonAlias(value = "name")
        String nombre,
        @JsonAlias(value = "birth_year")
        Integer nacimiento,
        @JsonAlias(value = "death_year")
        Integer fallecimiento
) {
}
