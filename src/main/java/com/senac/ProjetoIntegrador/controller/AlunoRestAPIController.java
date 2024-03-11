package com.senac.ProjetoIntegrador.controller;

import com.senac.ProjetoIntegrador.model.Aluno;
import com.senac.ProjetoIntegrador.service.AlunoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoRestAPIController {
    
    @Autowired
    AlunoService alunoService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<Aluno> addAluno(@RequestBody Aluno aluno) {
        
        var alunoCriado = alunoService.criar(aluno);
        
        return new ResponseEntity<>(alunoCriado, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listar(){
        
        List<Aluno> listagem = alunoService.listarTodos();
        
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        
        alunoService.excluir(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Aluno> pesquisar(@PathVariable Integer id){
        
        Aluno alunoEncontrado = alunoService.buscarPorId(id);
        
        return new ResponseEntity<>(alunoEncontrado,HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> editAluno(@PathVariable Integer id,@RequestBody Aluno aluno) {
        
        var alunoEditado = alunoService.atualizar(id, aluno);
        
        return new ResponseEntity<>(alunoEditado,HttpStatus.OK);
    }
    
}
