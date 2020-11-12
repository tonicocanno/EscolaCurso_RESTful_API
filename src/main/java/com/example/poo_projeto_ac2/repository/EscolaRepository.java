package com.example.poo_projeto_ac2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.poo_projeto_ac2.model.Curso;
import com.example.poo_projeto_ac2.model.Escola;

import org.springframework.stereotype.Component;

@Component
public class EscolaRepository {
    
    private List<Escola> escolas;
    private int nextCode;

    @PostConstruct
    public void criarEscola(){
        Escola e1 = new Escola();

        e1.setId(1);
        e1.setNome("FACENS");
        e1.setEndereco("Rod. Sen. José Ermirio de Moraes, 1425");
        e1.setDiretor("Paulo");

        escolas = new ArrayList<Escola>();
        escolas.add(e1);
        nextCode = 2;
    }

    public List<Escola> getTodasEscolas(){
        return escolas;
    }

    public Optional<Escola> getEscolaPorId(int id){
        for(Escola aux : escolas){
            if(aux.getId() == id){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }
    
    public Escola salvar(Escola escola){
        escola.setId(nextCode++);
        escolas.add(escola);
        return escola;
    }

    public void remove(Escola escola){
        if(escola.isTemCurso() == false){
            escolas.remove(escola);
        }
    }

    public Escola atualizar(Escola escola){
        Escola aux = getEscolaPorId(escola.getId()).get();

        if(aux != null){
            aux.setDiretor(escola.getDiretor());
            aux.setNome(escola.getNome());
        }
        return aux;
    }

    public void removeCurso(Curso curso){
        curso.getEscola().removeCurso(curso);
    }
}
