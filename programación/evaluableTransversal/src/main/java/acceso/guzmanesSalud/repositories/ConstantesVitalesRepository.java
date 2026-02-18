package acceso.guzmanesSalud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import acceso.guzmanesSalud.models.ConstantesVitales;




public interface ConstantesVitalesRepository extends JpaRepository<ConstantesVitalesRepository,Long> {
	public ConstantesVitales findById(long idConstantes);
	public  ConstantesVitales crearConstante(ConstantesVitales constantesVitales);
	

}
