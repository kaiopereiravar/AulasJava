package com.br.var.solutions.application.services.impl;

import com.br.var.solutions.application.services.useCase.ConverteSaldoEmDolarUseCase;
import org.springframework.stereotype.Service;


@Service
public class ConverteSaldoEmDolarCaseImpl implements ConverteSaldoEmDolarUseCase {

    public String conversaoSaldoEmDolarCase(double saldo){
        return converteSaldoEmDolar(saldo);
    }

    private String converteSaldoEmDolar(double saldo) {
        return String.valueOf(saldo / 5.11);
    }
}
