package com.example.poo_projeto_ac2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.poo_projeto_ac2.dto.CursoDTO;
import com.example.poo_projeto_ac2.model.Curso;
import com.example.poo_projeto_ac2.model.Escola;
import com.example.poo_projeto_ac2.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository repositorio;

    @Autowired
    private EscolaService escolaService;

    public Curso fromDTO(CursoDTO dto){
        Curso curso = new Curso();
        curso.setProfessor(dto.getProfessor());
        return curso;
    }

    public List<Curso> getTodosCursos(){
        return repositorio.getTodosCursos();
    }

    public Curso getCursoPorId(int id){
        Optional<Curso> op = repositorio.getCursoPorId(id);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }

    public Curso salvar(Curso curso, int id){
        Escola escola = escolaService.getEscolaPorId(id);
        curso.setEscola(escola);
        escola.addCurso(curso);
        escola.setTemCurso(true);
        return repositorio.salvar(curso);
    }

    public void removePorId(int id){

        //Para remover o curso da lista de cursos da escola
        escolaService.removeCurso(getCursoPorId(id));
        //somente remove do repositorio de cursos
        repositorio.remove(getCursoPorId(id));
    }

    public Curso atualizar(Curso curso){
        getCursoPorId(curso.getId());
        return repositorio.atualizar(curso);
    }

    public CursoDTO toDTO(Curso  curso){
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setProfessor(curso.getProfessor());
        dto.setDescricao(curso.getDescricao());
        dto.setCargaHoraria(curso.getCargaHoraria());
        return dto;
    }

    public List<CursoDTO> toListDTO(List<Curso> cursos){
        ArrayList<CursoDTO> listDTO = new ArrayList<CursoDTO>();
        for(Curso aux: cursos){
            listDTO.add(toDTO(aux));
        }
        return listDTO;
    }

}
