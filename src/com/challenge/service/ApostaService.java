package com.challenge.service;

import com.challenge.entity.ApostaEntity;
import com.challenge.repository.ApostaRepository;

import java.util.List;

public class ApostaService {

    ApostaRepository apostaRepository = new ApostaRepository();

    public ApostaService(){
        apostaRepository = new ApostaRepository();
    }
    public ApostaEntity salvarAposta(ApostaEntity aposta){
        //salvar no banco
        ApostaEntity apostaSalva = apostaRepository.salvarApostaDB(aposta);
        return apostaSalva;
    }

    public List<ApostaEntity> listarApostas(){
        List<ApostaEntity> listar = apostaRepository.listarApostas();
        listar.stream().forEach(System.out::println);
        return listar;
    }
}
