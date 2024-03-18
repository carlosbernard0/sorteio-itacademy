package com.challenge.service;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.ApuracaoEntity;
import com.challenge.repository.ApuracaoRepository;

public class ApuracaoService {
    ApuracaoRepository apuracaoRepository;
    public ApuracaoService(){
        apuracaoRepository = new ApuracaoRepository();
    }

    public ApuracaoEntity salvarApuracao(ApuracaoEntity apuracao){
        //salvar no banco
        ApuracaoEntity apuracaoSalva = apuracaoRepository.salvarApuracaoDB(apuracao);
        return apuracaoSalva;
    }
}
