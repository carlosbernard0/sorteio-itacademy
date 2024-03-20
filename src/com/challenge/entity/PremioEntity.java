package com.challenge.entity;

public class PremioEntity {
    private Integer idPremio;
    private Integer valorPremio;
    private String nomePremio;
    private Integer idAposta;
    private ApostaEntity aposta;

    public PremioEntity() {

    }

    public Integer getIdPremio() {
        return idPremio;
    }

    public void setIdPremio(Integer idPremio) {
        this.idPremio = idPremio;
    }

    public Integer getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(Integer valorPremio) {
        this.valorPremio = valorPremio;
    }

    public String getNomePremio() {
        return nomePremio;
    }

    public void setNomePremio(String nomePremio) {
        this.nomePremio = nomePremio;
    }

    public Integer getIdAposta() {
        return idAposta;
    }

    public void setIdAposta(Integer idAposta) {
        this.idAposta = idAposta;
    }

    public ApostaEntity getAposta() {
        return aposta;
    }

    public void setAposta(ApostaEntity aposta) {
        this.aposta = aposta;
    }
}
