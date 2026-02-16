package microservicios.primerProyectoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "microservicios.primerProyectoSpring.modelo") // Ajusta el paquete donde están tus entidades
public class PrimerProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimerProyectoSpringApplication.class, args);
	}

}
