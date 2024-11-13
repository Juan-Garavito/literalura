package com.reto.literalura.model;

public enum Lenguaje {
    all("all"),
    es("es"),
    en("en");

    private String lenguajeTag;

    Lenguaje(String lenguajeTag) {
        this.lenguajeTag = lenguajeTag;
    }

    public static Lenguaje fromLenguajes(String text){
        for (Lenguaje lenguaje : Lenguaje.values()){
            if(lenguaje.lenguajeTag.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun Lenguaje encontrado: " + text);
    }

}
