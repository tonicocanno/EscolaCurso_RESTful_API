package com.example.poo_projeto_ac2.service;

import java.util.List;
import java.util.Optional;

import com.example.poo_projeto_ac2.dto.EscolaDTO;
import com.example.poo_projeto_ac2.model.Curso;
import com.example.poo_projeto_ac2.model.Escola;
import com.example.poo_projeto_ac2.repository.EscolaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EscolaService {
    
    @Autowired
    private EscolaRepository repositorio;

    public Escola saveFromDTO(EscolaDTO dto){
        Escola escola = new Escola();
        escola.setNome(dto.getNome());
        escola.setDiretor(dto.getDiretor());
        escola.setEndereco(dto.getEndereco());
        return escola;
    }

    public Escola updateFromDTO(EscolaDTO dto){
        Escola escola = new Escola();
        escola.setNome(dto.getNome());
        escola.setDiretor(dto.getDiretor());
        escola.setEndereco(dto.getEndereco());
        return escola;
    }

    public List<Escola> getTodasEscolas(){
        return repositorio.getTodasEscolas();
    }

    public Escola getEscolaPorId(int id){
        Optional<Escola> op = repositorio.getEscolaPorId(id);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escola não encontrada"));
    }

    public Escola salvar(Escola escola){
        return repositorio.salvar(escola);
    }

    public void removePorId(int id){
        repositorio.remove(getEscolaPorId(id));
    }

    public Escola atualizar(Escola escola){
        getEscolaPorId(escola.getId());
        return repositorio.atualizar(escola);
    }

    public void removeCurso(Curso curso){
        repositorio.removeCurso(curso);
    }

}

