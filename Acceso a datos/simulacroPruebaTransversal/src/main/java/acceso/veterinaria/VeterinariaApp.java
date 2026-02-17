package acceso.veterinaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "acceso.veterinaria.models") 
public class VeterinariaApp {
  
	public static void main(String[] args) {
		SpringApplication.run(VeterinariaApp.class, args);
	}
  
}
