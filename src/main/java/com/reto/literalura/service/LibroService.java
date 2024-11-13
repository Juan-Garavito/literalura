package com.reto.literalura.service;

import com.reto.literalura.model.Lenguaje;
import com.reto.literalura.model.Libro;
import com.reto.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {


    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro guardarLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public List<Libro> buscarTodosLosLibros(){
        return libroRepository.findAll();
    }

    public Libro buscarLibroPorNombre(String titulo){
        return libroRepository.BuscarLibroPorNombre(titulo);
    }

    public List<Libro> buscarLibroPorLenguaje(Lenguaje lenguaje){
        return libroRepository.buscarLibroPorLenguaje(lenguaje);
    }

}
