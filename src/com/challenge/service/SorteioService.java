package com.challenge.service;

import com.challenge.entity.ApostaEntity;
import com.challenge.entity.SorteioEntity;
import com.challenge.repository.SorteioRepository;

public class SorteioService {

    SorteioRepository sorteioRepository;

    public SorteioService(){
        sorteioRepository = new SorteioRepository();
    }
    public SorteioEntity salvarSorteio(SorteioEntity sorteio){
        //salvar no bancod
        SorteioEntity sorteioSalvo = sorteioRepository.salvarSorteioDB(sorteio);
        return sorteioSalvo;
    }


}
