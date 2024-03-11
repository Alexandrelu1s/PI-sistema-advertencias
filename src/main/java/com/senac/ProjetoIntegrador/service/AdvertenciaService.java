package com.senac.ProjetoIntegrador.service;

import com.senac.ProjetoIntegrador.model.Advertencias;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senac.ProjetoIntegrador.repository.AdvertenciasRepository;

@Service
public class AdvertenciaService {
    
    @Autowired
    AdvertenciasRepository advertenciasRepository;
    
    public Advertencias criarAdvertencia(Advertencias advertencia) {
        advertencia.setId(null);
        advertenciasRepository.save(advertencia);
        
        return advertencia;
    }
    
    public Advertencias buscarAdvertenciaPorId(Integer id) {
        
        return advertenciasRepository.findById(id).orElseThrow();
    }
    
    public Advertencias atualizarAdvertencia(Integer id, Advertencias advertenciaEnviada) {
        
        Advertencias advertenciaEncontrada = buscarAdvertenciaPorId(id);
        advertenciaEncontrada.setAdvertencia(advertenciaEncontrada.getAdvertencia());
        advertenciasRepository.save(advertenciaEncontrada);
        
        return advertenciaEncontrada;
    }
    
    public void excluirAdvertencia(Integer id) {
        
        Advertencias advertenciaEncontrada = buscarAdvertenciaPorId(id);
        advertenciasRepository.deleteById(advertenciaEncontrada.getId());
    }
    
    public List<Advertencias> listarTodasAdvertenciasPorIdAluno(Integer id) {
        
        return advertenciasRepository.findByAlunoId(id);
    }
    
    public void excluirTodasAdvertenciasPorAluno(Integer id){
        
        for(Advertencias reg: listarTodasAdvertenciasPorIdAluno(id)){
            excluirAdvertencia(reg.getId());
        }
    }
    
}
