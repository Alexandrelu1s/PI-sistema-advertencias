package com.senac.ProjetoIntegrador.controller;

import com.senac.ProjetoIntegrador.model.Advertencias;
import com.senac.ProjetoIntegrador.service.AdvertenciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advertencia")
public class AdvertenciaRestAPIController {
    
    @Autowired
    AdvertenciaService advertenciaService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<Advertencias> addAdvertencia(@RequestBody Advertencias advertencia) {
        
        var advertenciaCriada = advertenciaService.criarAdvertencia(advertencia);
        
        return new ResponseEntity<>(advertenciaCriada, HttpStatus.CREATED);
    }
    
    @GetMapping("/pesquisar/{idAluno}")
    public ResponseEntity<List> pesquisar(@PathVariable Integer idAluno){
        
        List<Advertencias> lista = advertenciaService.listarTodasAdvertenciasPorIdAluno(idAluno);
        
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{idAdvertencia}")
    public ResponseEntity<?> delete(@PathVariable Integer idAdvertencia){
        
        advertenciaService.excluirAdvertencia(idAdvertencia);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir-todas-advertencias/{idAluno}")
    public ResponseEntity<?> deletePorAluno(@PathVariable Integer idAluno){
        
        advertenciaService.excluirTodasAdvertenciasPorAluno(idAluno);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
