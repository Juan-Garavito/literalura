package com.reto.literalura.repository;

import com.reto.literalura.model.Lenguaje;
import com.reto.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Libro BuscarLibroPorNombre(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.lenguajes = :lenguaje")
    List<Libro> buscarLibroPorLenguaje(Lenguaje lenguaje);
}
