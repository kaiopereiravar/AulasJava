package com.br.var.solutions.application.services.useCase;

import com.br.var.solutions.application.services.entities.InformacoesImc;

public interface ImcUseCase {

    InformacoesImc calculaImc(double peso, double altura);
}
