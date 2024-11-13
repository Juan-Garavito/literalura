package com.reto.literalura.service;

import com.reto.literalura.model.Autor;
import com.reto.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository =  autorRepository;
    }

    public Autor buscarAutor(String nombre){
        return autorRepository.buscarAutorPorNombre(nombre);
    }

    public Autor guardarAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public List<Autor> buscarTodosAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresPorFecha(Integer fecha){
        return autorRepository.buscarAutoresPorFecha(fecha);
    }
}
