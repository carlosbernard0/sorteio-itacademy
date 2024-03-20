package com.challenge.service;

import com.challenge.entity.ApuracaoEntity;
import com.challenge.entity.PremioEntity;
import com.challenge.repository.PremioRepository;

public class PremioService {
    PremioRepository premioRepository;

    public PremioService(){
        premioRepository = new PremioRepository();
    }

    public PremioEntity salvarPremio(PremioEntity premio){
        //salvar no banco
        PremioEntity premioSalvo = premioRepository.salvarPremioDB(premio);
        return premioSalvo;
    }
}
