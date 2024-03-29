package com.challenge.entity;

import java.sql.Array;
import java.util.List;

public class ApostaEntity {
    private Integer idAposta;
    private String nomeApostador;
    private String cpfApostador;
    private String numerosApostados;

    public ApostaEntity() {
    }

    public String getNumerosApostados() {
        return numerosApostados;
    }

    public void setNumerosApostados(String numerosApostados) {
        this.numerosApostados = numerosApostados;
    }

    public Integer getIdAposta() {
        return idAposta;
    }

    public void setIdAposta(Integer idAposta) {
        this.idAposta = idAposta;
    }

    public String getNomeApostador() {
        return nomeApostador;
    }

    public void setNomeApostador(String nomeApostador) {
        this.nomeApostador = nomeApostador;
    }



    public String getCpfApostador() {
        return cpfApostador;
    }

    public void setCpfApostador(String cpfApostador) {
        this.cpfApostador = cpfApostador;
    }

    @Override
    public String toString() {
        return "idAposta= " + idAposta +
                ", nomeApostador= " + nomeApostador +
                ", cpfApostador= " + cpfApostador +
                ", numerosApostados= " + numerosApostados;
    }
}
