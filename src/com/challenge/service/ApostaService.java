package com.challenge.service;

import com.challenge.entity.ApostaEntity;
import com.challenge.repository.ApostaRepository;

import java.util.List;

public class ApostaService {

    ApostaRepository apostaRepository;

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
        for (int i = 0; i < listar.size(); i++) {
            ApostaEntity aposta = listar.get(i);
            System.out.print("idAposta= " + aposta.getIdAposta() +
                    ", nomeApostador= " + aposta.getNomeApostador() +
                    ", cpfApostador= " + aposta.getCpfApostador() +
                    ", numerosApostados= " + aposta.getNumerosApostados()+"\n");
            
        }
        return listar;
    }

    public void excluir(){
        apostaRepository.excluir();
    }
}
