package acceso.veterinaria.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import acceso.veterinaria.models.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna,Long> {
	  List<Vacuna> findAll();
	  Set<Vacuna> findByPartida(String partida);
	  Vacuna findByNombre(String nombre);
	  Vacuna findByIdVacuna(long idVacuna);

}
