package com.senac.ProjetoIntegrador.controller;

import com.senac.ProjetoIntegrador.model.Advertencias;
import com.senac.ProjetoIntegrador.model.Aluno;
import com.senac.ProjetoIntegrador.service.AdvertenciaService;
import com.senac.ProjetoIntegrador.service.AlunoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController {
    
    @Autowired
    AlunoService alunoService;

    @Autowired
    AdvertenciaService advertenciaService;


    @GetMapping("/inicio")
    public String inicio(Model model) {
        
        return "index";
    }

    @GetMapping("/inserir")
    public String cadastro(Model model) {
        
        model.addAttribute("aluno", new Aluno());

        return "cadastro";
    }

    @GetMapping("/alterar")
    public String alterarAluno(Model model, @RequestParam String id) {
        
        Integer idAluno = Integer.parseInt(id);
        Aluno alunoEncontrado = alunoService.buscarPorId(idAluno);
        model.addAttribute("aluno", alunoEncontrado);

        return "cadastro";
    }

    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Aluno aluno, Model model) {
        
        if (aluno.getId() != null) {
            alunoService.atualizar(aluno.getId(), aluno);
        } else {
            alunoService.criar(aluno);
        }

        return "redirect:/listar";
    }

    @GetMapping("/excluir")
    public String excluirLivro(Model model, @RequestParam String id) {
        
        Integer idAluno = Integer.parseInt(id);
        advertenciaService.excluirTodasAdvertenciasPorAluno(idAluno);
        alunoService.excluir(idAluno);

        return "redirect:/listar";
    }

    @GetMapping("/listar")
    public String listagem(Model model) {
        
        model.addAttribute("listaAlunos", alunoService.listarTodos());
        return "lista-alunos";
    }

    @GetMapping("/excluirAnalise")
    public String exibirComentario(@RequestParam String id, Model model) {
        
        Integer idAdvertencia = Integer.parseInt(id);
        Advertencias objAdvertencia = advertenciaService.buscarAdvertenciaPorId(idAdvertencia);
        advertenciaService.excluirAdvertencia(idAdvertencia);
        Integer idAluno = objAdvertencia.getAluno().getId();

        return "redirect:/exibir?id=" + idAluno;
    }

    @GetMapping("/exibir")
    public String exibirDados(@RequestParam String id, Model model) {
        
        Integer idAluno = Integer.parseInt(id);

        Aluno alunoEncontrado = new Aluno();
        alunoEncontrado = alunoService.buscarPorId(idAluno);

        List<Advertencias> advertenciasEncontrada = new ArrayList<>();
        advertenciasEncontrada = advertenciaService.listarTodasAdvertenciasPorIdAluno(idAluno);

        model.addAttribute("aluno", alunoEncontrado);
        model.addAttribute("advertencia", new Advertencias());
        model.addAttribute("advertencias", advertenciasEncontrada);

        return "detalhes";
    }

    @PostMapping("/gravar-advertencia")
    public String processarComentario(@ModelAttribute Aluno aluno, @ModelAttribute Advertencias advertencia, Model model) {
        
        advertencia.setAluno(aluno);
        advertenciaService.criarAdvertencia(advertencia);

        return "redirect:/exibir?id=" + aluno.getId();
    }
}
