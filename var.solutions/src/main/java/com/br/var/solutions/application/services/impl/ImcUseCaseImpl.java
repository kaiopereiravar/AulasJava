package com.br.var.solutions.application.services.impl;

import com.br.var.solutions.application.services.entities.InformacoesImc;
import com.br.var.solutions.application.services.useCase.ImcUseCase;
import org.springframework.stereotype.Service;

@Service
public class ImcUseCaseImpl implements ImcUseCase {

    public InformacoesImc calculaImc(double peso, double altura){
        return calculoImc(peso, altura);
    }

    private InformacoesImc calculoImc(double peso, double altura) {
        double imc = peso / (altura * altura);

        if (imc < 18.5) {
            return InformacoesImc.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("abaixo do peso.")
                    .build();
        }
        else if (imc >= 18.5 && imc <= 24.9) {
            return InformacoesImc.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("peso ideal.")
                    .build();
        }
        else if (imc > 24.9 && imc <= 29.9) {
            return InformacoesImc.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("excesso de peso.")
                    .build();
        }
        else if (imc > 29.9 && imc <= 34.9) {
            return InformacoesImc.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("obesidade classe 1.")
                    .build();
        }
        else if (imc > 34.9 && imc <= 39.9) {
            return InformacoesImc.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("obesidade classe 2.")
                    .build();
        }
        else {
            return InformacoesImc.builder()
                    .imc(String.valueOf(imc))
                    .classificacao("obesidade classe 3.") //Como está agora
                    .build();

//            imcCalculado.setImc(String.valueOf(imc));  //Como estava antes
//            imcCalculado.setClassificacao("obesidade classe 3.");
//            return imcCalculado;
        }
    }
}
