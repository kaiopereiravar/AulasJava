package com.br.var.solutions.application.services.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter //Essas anotações é para evitar aquele monte de getters e setters feitos a mão
@Setter //
@Builder
public class InformacoesImc {
    private String imc;
    private String classificacao;
}
