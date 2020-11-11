package com.example.poo_projeto_ac2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.poo_projeto_ac2.dto.EscolaDTO;
import com.example.poo_projeto_ac2.model.Curso;
import com.example.poo_projeto_ac2.model.Escola;
import com.example.poo_projeto_ac2.service.CursoService;
import com.example.poo_projeto_ac2.service.EscolaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/escolas")
public class EscolaController {
    
    @Autowired
    private EscolaService escolaService;

    @Autowired
    private CursoService cursoService;

    @GetMapping()
    public List<Escola> getEscolas(){
        return escolaService.getTodasEscolas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> getEscola(@PathVariable int id){
        Escola escola = escolaService.getEscolaPorId(id);
        return ResponseEntity.ok(escola);
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody EscolaDTO escolaDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Escola escola = escolaService.saveFromDTO(escolaDTO);
        escola = escolaService.salvar(escola);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + escola.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id){
        escolaService.removePorId(id);
        return ResponseEntity.noContent().build();
    }

    //Unicos dados que poderao ser atualizados são: nome e diretor(a)
    @PutMapping("/{id}")
    public ResponseEntity<Escola> atualizar(@PathVariable int id, @RequestBody EscolaDTO escolaDTO){
        Escola escola = escolaService.updateFromDTO(escolaDTO);
        escola.setId(id);
        escola = escolaService.atualizar(escola);
        return ResponseEntity.ok(escola);
    }

    @PostMapping("{id}/cursos")
    public ResponseEntity<Void> salvar(@PathVariable int id, @RequestBody Curso curso, HttpServletRequest request, UriComponentsBuilder builder){
        curso = cursoService.salvar(curso, id);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + curso.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("{id}/cursos")
    public ResponseEntity<List<Curso>> getTodosCurosDaEscola(@PathVariable int id){
        Escola escola = escolaService.getEscolaPorId(id);
        return ResponseEntity.ok(escola.getCursos());
    }

}
