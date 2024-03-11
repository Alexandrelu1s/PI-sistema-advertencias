package com.senac.ProjetoIntegrador.repository;

import com.senac.ProjetoIntegrador.model.Advertencias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertenciasRepository extends JpaRepository<Advertencias, Integer>{
    
    List<Advertencias> findByAlunoId(Integer id);
}
