package com.br.var.solutions.application.services.useCase;

import com.br.var.solutions.application.services.entities.InformacoesImc;
import com.br.var.solutions.adapters.input.entities.PessoaRequest;
import com.br.var.solutions.adapters.input.entities.PessoaResponse;

public interface MontarRespostaFrontEndUseCase{

    //na aula de lincagem com o banco de dados ele mostra que ele declarou o nome desse método como "mapper"
    PessoaResponse montandoRespostaFrontEnd(PessoaRequest pessoinhaa, InformacoesImc imc, int anoNascimento,
                                            String impostoRenda, String validaMundial, String SaldoEmDolar);

}
