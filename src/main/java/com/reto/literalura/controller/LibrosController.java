package com.reto.literalura.controller;

import com.reto.literalura.dto.AutorDTO;
import com.reto.literalura.dto.LibroDTO;
import com.reto.literalura.model.Autor;
import com.reto.literalura.model.Lenguaje;
import com.reto.literalura.model.Libro;
import com.reto.literalura.service.AutorService;
import com.reto.literalura.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LibrosController {

    private LibroService libroService;
    private AutorService autorService;

    public LibrosController(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Libro> libros = libroService.buscarTodosLosLibros();
        model.addAttribute("libros", libros);
        return "index";
    }

    @GetMapping("/autores")
    public String autores(Model model){
        List<Autor> autores = autorService.buscarTodosAutores();
        model.addAttribute("autores", autores);
        return "autores";
    }

    @GetMapping("/lenguaje/{lenguaje}")
    @ResponseBody
    public List<LibroDTO> filtrarLibrosPorLenguaje(@PathVariable String lenguaje){
        Lenguaje len = Lenguaje.fromLenguajes(lenguaje);
        if(len.equals(Lenguaje.all)){
            List<Libro> libros = libroService.buscarTodosLosLibros();
            List<LibroDTO>  librosDto = convetirLibroALibroDTO(libros);
            return librosDto;
        }
        List<Libro> libros = libroService.buscarLibroPorLenguaje(len);
        List<LibroDTO>  librosDto = convetirLibroALibroDTO(libros);
        return librosDto;
    }

    @GetMapping("/fecha/{fecha}")
    @ResponseBody
    public List<AutorDTO> filtrarAutoresPorFecha(@PathVariable Integer fecha){
        List<Autor> autores = autorService.buscarAutoresPorFecha(fecha);
        List<AutorDTO> autoresDTO = convetirAutorAAutorDTO(autores);
        return autoresDTO;
    }

    private List<AutorDTO> convetirAutorAAutorDTO(List<Autor> autores) {
        List<AutorDTO> autoresDTO = autores.stream().map(autor -> new AutorDTO(
                autor.getNombre(),
                autor.getNacimiento(),
                autor.getFallecimiento()
        )).collect(Collectors.toList());

        return autoresDTO;
    }

    public List<LibroDTO> convetirLibroALibroDTO(List<Libro> libros){
        List<LibroDTO>  librosDto = libros.stream().map(libro -> new LibroDTO(
                libro.getTitulo(),
                libro.getLenguajes(),
                libro.getPoster(),
                libro.getDescargas()
        )).collect(Collectors.toList());

        return librosDto;
    }

}
