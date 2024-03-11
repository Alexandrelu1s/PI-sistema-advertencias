package com.senac.ProjetoIntegrador.service;

import com.senac.ProjetoIntegrador.model.Aluno;
import com.senac.ProjetoIntegrador.repository.AlunoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    
    @Autowired
    AlunoRepository alunoRepository;
    
    public Aluno criar(Aluno aluno) {
        aluno.setId(null);
        alunoRepository.save(aluno);
        return aluno;
    }
    
    public List<Aluno> listarTodos(){
        
        return alunoRepository.findAll();
    }
    
    public Aluno buscarPorId(Integer id) {
        return alunoRepository.findById(id).orElseThrow();
    }
    
    public void excluir(Integer id) {
        Aluno alunoEncontrado = buscarPorId(id);
        alunoRepository.deleteById(alunoEncontrado.getId());
    }
    
    public Aluno atualizar(Integer id, Aluno alunoEnviado) {
        Aluno alunoEncontrado = buscarPorId(id);
        alunoEncontrado.setNome(alunoEnviado.getNome());
        alunoEncontrado.setSobrenome(alunoEnviado.getSobrenome());
        alunoEncontrado.setCpf(alunoEnviado.getCpf());
        alunoEncontrado.setTurma(alunoEnviado.getTurma());
        alunoRepository.save(alunoEncontrado);
        return alunoEncontrado;
    }

    
    
}
