package com.challenge.entity;

import java.util.Date;

public class ApuracaoEntity {
    private Integer idApuracao;
    private Integer idSorteio;
    private SorteioEntity sorteio;
    private Date dataRealizada;
    private String apostasVencedoras;
    private Integer idAposta;
    private ApostaEntity aposta;

    public Date getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(Date dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public Integer getIdApuracao() {
        return idApuracao;
    }

    public void setIdApuracao(Integer idApuracao) {
        this.idApuracao = idApuracao;
    }

    public Integer getIdSorteio() {
        return idSorteio;
    }

    public void setIdSorteio(Integer idSorteio) {
        this.idSorteio = idSorteio;
    }

    public SorteioEntity getSorteio() {
        return sorteio;
    }

    public void setSorteio(SorteioEntity sorteio) {
        this.sorteio = sorteio;
    }



    public String getApostasVencedoras() {
        return apostasVencedoras;
    }

    public void setApostasVencedoras(String apostasVencedoras) {
        this.apostasVencedoras = apostasVencedoras;
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
