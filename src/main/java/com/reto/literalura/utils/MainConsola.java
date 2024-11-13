package com.reto.literalura.utils;

import com.reto.literalura.model.Autor;
import com.reto.literalura.model.Lenguaje;
import com.reto.literalura.model.Libro;
import com.reto.literalura.service.AutorService;
import com.reto.literalura.service.LibroService;

import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainConsola {

    private Scanner teclado;
    private Peticion peticion;
    private ConvertirDatos convertirDatos;
    private LibroService libroService;
    private AutorService autorService;

    public MainConsola(LibroService libroService, AutorService autorService) {
        teclado = new Scanner(System.in);
        peticion = new Peticion();
        convertirDatos = new ConvertirDatos();
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void inciarConsola(){
        int opt;
        do {
            System.out.println("*************************************");
            System.out.println("1.Buscar Libro");
            System.out.println("2.Listar Libros");
            System.out.println("3.Listar Autores");
            System.out.println("4.Listar Libros Por Lenguajes");
            System.out.println("5.Buscar Actores Por Fecha");
            System.out.println("0.Cerrar Aplicación");
            System.out.println("*************************************");
            opt = teclado.nextInt();
            teclado.nextLine();
            switch (opt){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    List<Libro> libros = libroService.buscarTodosLosLibros();
                    listarLibros(libros);
                    break;
                case 3:
                    List<Autor> autores = autorService.buscarTodosAutores();
                    listarAutores(autores);
                    break;
                case 4:
                    listarPorLenguajes();
                    break;
                case 5:
                    buscarActoresPorFecha();
                    break;
                case 0:
                    System.out.println("Adiooooos");
                    teclado.close();
                    break;
                default:
                    System.out.println("Esta no es una opción");
                    break;
            }
        }while (opt != 0);
    }

    private void buscarActoresPorFecha() {
        System.out.println("Ingresa una fecha");
        String fecha = teclado.nextLine();
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(fecha);
        if(matcher.matches()){
            List<Autor> autores = autorService.buscarAutoresPorFecha(Integer.valueOf(fecha));
            if (autores.size() <= 0 ){
                System.out.println("No hay autores en estas fechas");
                return;
            }

            listarAutores(autores);
            return;
        }

        System.out.println("Hay un error en la fecha");

    }

    private void listarPorLenguajes() {
        System.out.println("Elije el lenguaje que deseas buscar");
        System.out.println("1. Para libros en español");
        System.out.println("2. Para libros en ingles");
        Integer intLenguaje = teclado.nextInt();
        List<Libro> libros;
        switch (intLenguaje){
            case 1:
                Lenguaje lenguajeEspañol = Lenguaje.fromLenguajes("es");
                libros = libroService.buscarLibroPorLenguaje(lenguajeEspañol);
                listarLibros(libros);
                break;
            case 2:
                Lenguaje lenguajeIngles = Lenguaje.fromLenguajes("en");
                libros = libroService.buscarLibroPorLenguaje(lenguajeIngles);
                listarLibros(libros);
                break;
            default:
                System.out.println("Esta no es una opción");
                break;
        }


    }

    private void listarAutores(List<Autor> autores) {
        String template = """
                ----------------------
                Nombre: %s
                Nacimiento: %s
                Fallecimiento: %s
                ----------------------
                """;
        autores.forEach(autor -> {
            System.out.println(template.formatted(autor.getNombre(),autor.getNacimiento(),autor.getFallecimiento()));
        });
    }

    private void listarLibros(List<Libro> libros) {
        String template = """
                ----------------------
                Titulo: %s
                Autor: %s
                Lenguaje: %s
                Descargas: %.1f
                ----------------------
                """;

        libros.forEach(libro -> {
            System.out.println(template.formatted(libro.getTitulo(),
                    libro.getAutor().getNombre(),
                    libro.getLenguajes(),
                    libro.getDescargas()));

        });
    }

    private void buscarLibro() {
        System.out.println("¿Que libro buscas?");
        String busqueda = teclado.nextLine();
        busqueda = URLEncoder.encode(busqueda);
        String json = peticion.hacerPeticion("https://gutendex.com/books/?search="+busqueda);
        ResponseLibros responseLibros = (ResponseLibros) convertirDatos.ConvertirAClase(json, ResponseLibros.class);
        Optional<DatosLibros> datosLibros = responseLibros.datosLibros().stream().findFirst();
        if (datosLibros.isPresent()) {
            guardarLibro(datosLibros.get());
            return;
        }

        System.out.println("Libro no encontrado");
    }
    
    private void guardarLibro(DatosLibros datosLibros){
   
            System.out.println(datosLibros);

            Libro libroGuardado = libroService.buscarLibroPorNombre(datosLibros.titulo()) ;
            if(libroGuardado != null){
                System.out.println("Este libro ya esta guardado");
                return;
            }

            DatosAutores datosAutor = datosLibros.autores().stream().findFirst().get();
            Autor autor = autorService.buscarAutor(datosAutor.nombre()) ;
            if (autor != null){
                Libro libro = new Libro(datosLibros, autor);
                libroService.guardarLibro(libro);
                return;
            }

            autor = autorService.guardarAutor(new Autor(datosAutor));
            Libro libro = new Libro(datosLibros, autor);
            libroService.guardarLibro(libro);
    }
}
