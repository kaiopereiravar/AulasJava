package com.br.var.solutions.application.services.impl;

import com.br.var.solutions.application.services.entities.InformacoesImc;
import com.br.var.solutions.adapters.input.entities.PessoaRequest;
import com.br.var.solutions.adapters.input.entities.PessoaResponse;
import com.br.var.solutions.application.services.useCase.MontarRespostaFrontEndUseCase;
import org.springframework.stereotype.Service;

@Service
public class MontarRespostaFrontEndCaseImpl implements MontarRespostaFrontEndUseCase {

    public PessoaResponse montandoRespostaFrontEnd(PessoaRequest pessoinhaa, InformacoesImc imc, int anoNascimento,
                                                  String impostoRenda, String validaMundial, String SaldoEmDolar){

        return montarRespostaFrontEnd( pessoinhaa,  imc,  anoNascimento,
         impostoRenda,  validaMundial,  SaldoEmDolar);
    }


    private PessoaResponse montarRespostaFrontEnd(PessoaRequest pessoinhaa, InformacoesImc imc, int anoNascimento,
                                                  String impostoRenda, String validaMundial, String SaldoEmDolar) {

        PessoaResponse response = new PessoaResponse();

        response.setNome(pessoinhaa.getNome());
        response.setSobrenome(pessoinhaa.getSobrenome());
        response.setSalario(impostoRenda);
        response.setAnoNascimento(anoNascimento);
        response.setMundialClubes(validaMundial);
        response.setSaldoEmDolar(SaldoEmDolar);
        response.setImc(imc.getImc());
        response.setClassificacao(imc.getClassificacao());
        response.setIdade(pessoinhaa.getIdade());
        response.setAltura(pessoinhaa.getAltura());
        response.setPeso(pessoinhaa.getPeso());
        response.setSaldo(pessoinhaa.getSaldo());
        response.setEndereco(pessoinhaa.getEndereco());
        response.setTime(pessoinhaa.getTime());

        return response;
    }
}
