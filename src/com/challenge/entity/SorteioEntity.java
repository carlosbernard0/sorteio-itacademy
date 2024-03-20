package com.challenge.entity;


import java.sql.Array;
import java.util.List;

public class SorteioEntity  {
    private Integer idSorteio;
    private String numerosSorteados;

    private Integer totalApostas;
    private String numerosApostas;

    public String getNumerosApostas() {
        return numerosApostas;
    }

    public void setNumerosApostas(String numerosApostas) {
        this.numerosApostas = numerosApostas;
    }

    public Integer getTotalApostas() {
        return totalApostas;
    }

    public void setTotalApostas(Integer totalApostas) {
        this.totalApostas = totalApostas;
    }



    public Integer getIdSorteio() {
        return idSorteio;
    }

    public void setIdSorteio(Integer idSorteio) {
        this.idSorteio = idSorteio;
    }

    public String getNumerosSorteados() {
        return numerosSorteados;
    }

    public void setNumerosSorteados(String numerosSorteados) {
        this.numerosSorteados = numerosSorteados;
    }

}
