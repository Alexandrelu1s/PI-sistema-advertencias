package com.senac.ProjetoIntegrador.controller;

import com.senac.ProjetoIntegrador.model.Advertencia;
import com.senac.ProjetoIntegrador.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController {
    
    private List<Aluno> listaAlunos = new ArrayList<>();
    private List<Advertencia> listaAdvertencias = new ArrayList<>();
    
    @GetMapping("/inicio")
    public String incio(){
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String cadastro(Model model){
        model.addAttribute("aluno", new Aluno());
        return "cadastro";
    }
    
    @PostMapping("/salvar")
    public String processarFormulario(Model model, @ModelAttribute Aluno aluno){
        aluno.setId(listaAlunos.size() + 1);
        
        listaAlunos.add(aluno);
        
        return "redirect:/listar";
    }
    
    @GetMapping("/listar")
    public String listagem(Model model){
        model.addAttribute("listaAlunos", listaAlunos);
        return "lista-alunos";
    }
    
    @GetMapping("/exibir")
    public String exibirDados(@RequestParam String id ,Model model){
        Integer idAluno = Integer.parseInt(id);
        
        Aluno alunoEncontrado = new Aluno();
        for(Aluno a : listaAlunos){
            if(a.getId() == idAluno){
                alunoEncontrado = a;
                break;
            }
        }
        
        List<Advertencia> advertenciasEncontradas = new ArrayList<>();
        for(Advertencia adv : listaAdvertencias){
            if(adv.getAluno().getId() == idAluno){
                advertenciasEncontradas.add(adv);
            }
        }
        
        model.addAttribute("aluno", alunoEncontrado);
        model.addAttribute("advertencia", new Advertencia());
        model.addAttribute("advertencias", advertenciasEncontradas);
        return "detalhes";
    }
    
    @PostMapping("/gravar-advertencia")
    public String processarAdvertencia(Model model, @ModelAttribute Aluno aluno, @ModelAttribute Advertencia advertencia){
        
        advertencia.setId(listaAdvertencias.size() + 1);
        advertencia.setAluno(aluno);
        listaAdvertencias.add(advertencia);
        
        return "redirect:/listar";
    }
}
