package com.modelo1NIntAuto._IntAuto.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo1NIntAuto._IntAuto.modelo.Empleado;
import com.modelo1NIntAuto._IntAuto.modelo.Puesto;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findById(Integer id);
    List<Empleado> findAll();
    Empleado save(Empleado e);
    void deleteById(Integer id);
    boolean existsById(Integer id);

    // Filtros N:1
    List<Empleado> findByEmpresaId(int idEmpresa);
    List<Empleado> findByPuesto(Puesto puesto);
    List<Empleado> findBySalarioGreaterThanEqual(double salario);
}
