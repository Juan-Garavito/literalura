package com.reto.literalura.utils;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias(value = "title")
        String titulo,
        @JsonAlias(value = "authors")
        List<DatosAutores> autores,
        @JsonAlias(value = "formats")
        Map<String, String> portadas,
        @JsonAlias(value = "languages")
        List<String> lenguajes,
        @JsonAlias(value = "download_count")
        double descargas
) {
}
