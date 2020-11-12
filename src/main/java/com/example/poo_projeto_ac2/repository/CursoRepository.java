package com.example.poo_projeto_ac2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.poo_projeto_ac2.model.Curso;

import org.springframework.stereotype.Component;

@Component
public class CursoRepository {

    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    private static int nextId = 1;

    public List<Curso> getTodosCursos(){
        return cursos;
    }

    public Optional<Curso> getCursoPorId(int id){
        for(Curso aux : cursos){
            if(aux.getId() == id){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Curso salvar(Curso curso){
        curso.setId(nextId++);
        cursos.add(curso);
        return curso;
    }

    public void remove(Curso curso){
        cursos.remove(curso);
    }

    public Curso atualizar(Curso curso){
        Curso aux = getCursoPorId(curso.getId()).get();
        if(aux != null){
            aux.setProfessor(curso.getProfessor());
        }
        return aux;
    }

}
