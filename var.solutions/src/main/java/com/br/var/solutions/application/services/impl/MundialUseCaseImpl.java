package com.br.var.solutions.application.services.impl;

import com.br.var.solutions.application.services.useCase.MundialUseCase;
import org.springframework.stereotype.Service;

@Service
public class MundialUseCaseImpl implements MundialUseCase {

    public String calculoMundial(String time){
        return calculaMundial(time); // quando chegar requisição aqui, ele vai passar o método privado da classe
    }

    private String calculaMundial(String time) {

        if (time.equalsIgnoreCase("Corinthians")) { //se ele for igual e é para ignorar letras maiusculas e minusculas
            return "Parabens o seu time possui 2 mundiais de clubes vonforme a FIFA";
        } else if (time.equalsIgnoreCase("São Paulo")) {
            return "Parabens o seu time possui 3 mundiais de clubes vonforme a FIFA";
        } else if (time.equalsIgnoreCase("Santos")) {
            return "Parabens o seu time possui 2 mundiais de clubes vonforme a FIFA";
        } else {
            return "Poxa, que pena, continue torcendo para seu clube ganhar o mundial!";
        }
    }
}
