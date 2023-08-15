package com.br.var.solutions.application.services.impl;

import com.br.var.solutions.application.services.useCase.AnoNascimentoUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AnoNascimentoCaseImpl implements AnoNascimentoUseCase {

    public int calculaAnoNascimento(int idade){
        return calculoAnoNascimento(idade);
    }

    private int calculoAnoNascimento(int idade) {
        LocalDate dataLcal = LocalDate.now();

        int anoAtual = dataLcal.getYear();
        return anoAtual - idade;
    }
}




