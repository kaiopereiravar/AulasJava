package com.br.var.solutions.adapters.input.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class GenerateToken {
    private String token;
    private Date expireEm;
    private long tempoValidacao;
    private String solicitante;

//    public String getToken() {
//        return token;
//    }
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Date getExpireEm() {
//        return expireEm;
//    }
//
//    public void setExpireEm(Date expireEm) {
//        this.expireEm = expireEm;
//    }
//
//    public long getTempoValidacao() {
//        return tempoValidacao;
//    }
//
//    public void setTempoValidacao(long tempoValidacao) {
//        this.tempoValidacao = tempoValidacao;
//    }
//
//    public String getSolicitante() {
//        return solicitante;
//    }
//
//    public void setSolicitante(String solicitante) {
//        this.solicitante = solicitante;
//    }
}

