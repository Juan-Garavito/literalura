package com.reto.literalura;

import com.reto.literalura.repository.LibroRepository;
import com.reto.literalura.service.AutorService;
import com.reto.literalura.service.LibroService;
import com.reto.literalura.utils.ConvertirDatos;
import com.reto.literalura.utils.MainConsola;
import com.reto.literalura.utils.Peticion;
import com.reto.literalura.utils.ResponseLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	LibroService libroService;
	@Autowired
	AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		MainConsola mainConsola = new MainConsola(libroService, autorService);
		mainConsola.inciarConsola();
	}
}
