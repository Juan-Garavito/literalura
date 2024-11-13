package com.reto.literalura.dto;

import com.reto.literalura.model.Lenguaje;


public record LibroDTO(
        String titulo,
        Lenguaje lenguajes,
        String poster,
        double descargas
) {
  
}
