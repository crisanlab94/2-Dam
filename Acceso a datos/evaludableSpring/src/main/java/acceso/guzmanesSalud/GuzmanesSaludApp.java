package acceso.guzmanesSalud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "acceso.guzmanesSalud.models") 
public class GuzmanesSaludApp {
  
	public static void main(String[] args) {
		SpringApplication.run(GuzmanesSaludApp.class, args);
	}
  
}
 