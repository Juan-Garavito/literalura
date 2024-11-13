package com.reto.literalura.repository;

import com.reto.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    Autor buscarAutorPorNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :fecha AND a.fallecimiento > :fecha")
    List<Autor> buscarAutoresPorFecha(Integer fecha);
}
