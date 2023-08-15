package com.br.var.solutions.application.services.impl;

import com.br.var.solutions.application.services.useCase.CalculaFaixaImpostoRendaCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculaFaixaImpostoRendaCaseImpl implements CalculaFaixaImpostoRendaCase {

    public String calculoFaixaImpostoRenda(double salario){
        return calculaFaixaImpostoRenda(salario);
    }

    private String calculaFaixaImpostoRenda(double salario) {
        log.info("Iniciando o calculo do imposto de renda: " + salario);
        String novoSalarioCalculado;

        if (salario < 1903.98) {
            return "isenta";
        } else if (salario > 1903.98 && salario < 2826.65) {
            double calculoIR = 142.80 - ((salario * 0.075) / 100);
            double novoSalario = salario - calculoIR;

            novoSalarioCalculado = String.valueOf(novoSalario); //ele colocou isso pois o nosso metodo retorna uma string contatenada
            return novoSalarioCalculado;
        } else if (salario >= 2826.66 && salario < 3751.05) {
            double calculoIR = 354.80 - ((salario * 0.15) / 100);
            double novoSalario = salario - calculoIR;

            novoSalarioCalculado = String.valueOf(novoSalario);
            return novoSalarioCalculado;
        } else if (salario >= 3751.06 && salario < 4664.68) {
            double calculoIR = 636.13 - ((salario * 0.225) / 100);
            double novoSalario = salario - calculoIR;

            novoSalarioCalculado = String.valueOf(novoSalario);
            return novoSalarioCalculado;
        } else {
            double calculoIRF = 869.36 - ((salario * 275) / 100);
            double novoSalario = salario - calculoIRF;

            novoSalarioCalculado = String.valueOf(novoSalario);
            return novoSalarioCalculado;
        }
    }
}
