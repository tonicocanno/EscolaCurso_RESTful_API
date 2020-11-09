package com.example.poo_projeto_ac2.controller;

import java.util.List;

import com.example.poo_projeto_ac2.dto.CursoDTO;
import com.example.poo_projeto_ac2.model.Curso;
import com.example.poo_projeto_ac2.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService service;

    @GetMapping()
    public List<Curso> getTodosCursos(){
        return service.getTodosCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoPorId(@PathVariable int id){
        Curso curso = service.getCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id){

        service.removePorId(id);
        return ResponseEntity.noContent().build();
    }

    //Unico dado que podera ser atualizado e': professor(a)
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable int id, @RequestBody CursoDTO cursoDTO){
        Curso curso = service.fromDTO(cursoDTO);
        curso.setId(id);
        curso = service.atualizar(curso);
        return ResponseEntity.ok(curso);
    }

}
