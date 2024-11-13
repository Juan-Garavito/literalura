package com.reto.literalura.model;

import com.reto.literalura.utils.DatosLibros;
import jakarta.persistence.*;


@Entity
@Table(name = "Libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Lenguaje lenguajes;
    private String poster;
    private double descargas;

    public Libro() {
    }

    public Libro(String titulo, Autor autor, Lenguaje lenguajes, double descargas, String poster) {
        this.titulo = titulo;
        this.autor = autor;
        this.lenguajes = lenguajes;
        this.descargas = descargas;
        this.poster = poster;
    }

    public Libro(DatosLibros datosLibros, Autor autor) {
        this.titulo = datosLibros.titulo();
        this.lenguajes = Lenguaje.fromLenguajes(datosLibros.lenguajes().stream().findFirst().get());
        this.descargas = datosLibros.descargas();
        this.autor = autor;
        this.poster = datosLibros.portadas().get("image/jpeg");
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Lenguaje getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Lenguaje lenguajes) {
        this.lenguajes = lenguajes;
    }

    public double getDescargas() {
        return descargas;
    }

    public void setDescargas(double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", lenguajes='" + lenguajes + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}
