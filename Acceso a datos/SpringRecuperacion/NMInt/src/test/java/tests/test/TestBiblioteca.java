package tests.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modeloNMInt._Int.Application;
import com.modeloNMInt._Int.modelo.Autor;
import com.modeloNMInt._Int.modelo.Libro;
import com.modeloNMInt._Int.service.BibliotecaService;


public class TestBiblioteca {
	    private static final Logger logger = LoggerFactory.getLogger(TestBiblioteca.class);

	    public static void main(String[] args) {
	        ApplicationContext ctx = SpringApplication.run(Application.class, args);
	        BibliotecaService service = ctx.getBean(BibliotecaService.class);

	        logger.info("======= TEST N:M ID MANUAL INT TOTAL =======");

	        try {
	            // 1. REGISTRO
	            service.registrarAutor(new Autor(1, "Cervantes", "Española"));
	            service.registrarAutor(new Autor(2, "Shakespeare", "Inglesa"));
	            service.registrarLibro(new Libro(101, "El Quijote", "Novela"));
	            service.registrarLibro(new Libro(102, "Hamlet", "Tragedia"));

	            // 2. VINCULACIÓN
	            service.vincularAutorConLibro(1, 101);
	            service.vincularAutorConLibro(2, 102);

	            // 3. PROBAR FILTROS DE NAVEGACIÓN
	            logger.info(">>> TEST: Autores que han escrito 'Quijote'");
	            service.buscarAutoresPorLibro("Quijote").forEach(a -> logger.info(" - {}", a.getNombre()));

	            logger.info(">>> TEST: Libros de Shakespeare");
	            service.buscarLibrosPorAutor("Shakespeare").forEach(l -> logger.info(" - {}", l.getTitulo()));

	            // 4. PROBAR EXCEPCIONES
	            logger.info(">>> TEST: Forzando errores...");
	            try { service.findAutorById(999); } catch (Exception e) { logger.error("OK: {}", e.getMessage()); }
	            try { service.registrarLibro(new Libro(101, "Repetido", "G")); } catch (Exception e) { logger.error("OK: {}", e.getMessage()); }

	        } catch (Exception e) {
	            logger.error("ERROR: {}", e.getMessage());
	        }
	    }
	}

