package com.modelo1NIntAuto._IntAuto.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo1NIntAuto._IntAuto.modelo.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    Optional<Empresa> findById(Integer id);
    List<Empresa> findAll();
    Empresa save(Empresa e);
    void deleteById(Integer id);
    boolean existsById(Integer id);

    // Filtros extra
    List<Empresa> findByEsMultinacionalTrue();
}
