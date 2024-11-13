package com.reto.literalura.model;

import com.reto.literalura.utils.DatosAutores;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    Integer nacimiento;
    Integer fallecimiento;
    @OneToMany(mappedBy = "autor")
    List<Libro> libros;

    public Autor() {
    }

    public Autor(String nombre, Integer nacimiento, Integer fallecimiento, List<Libro> libros) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
        this.libros = libros;
    }

    public Autor(DatosAutores datosAutores) {
        this.nombre = datosAutores.nombre();
        this.nacimiento = datosAutores.nacimiento();
        this.fallecimiento = datosAutores.fallecimiento();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", fallecimiento=" + fallecimiento +
                ", libros=" + libros +
                '}';
    }
}
