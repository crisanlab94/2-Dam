package acceso.guzmanesSalud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import acceso.guzmanesSalud.models.ConstantesVitales;




public interface ConstantesVitalesRepository extends JpaRepository<ConstantesVitales,Long> {

public  ConstantesVitales crearConstante(ConstantesVitales constantesVitales);
	

}
