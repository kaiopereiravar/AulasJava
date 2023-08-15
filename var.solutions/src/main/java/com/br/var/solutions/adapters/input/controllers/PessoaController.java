package com.br.var.solutions.adapters.input.controllers;

import com.br.var.solutions.adapters.input.entities.PessoaRequest;
import com.br.var.solutions.adapters.input.entities.PessoaResponse;
import com.br.var.solutions.application.services.entities.InformacoesImc;
import com.br.var.solutions.application.services.useCase.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*")//posso receber chamadas de todas origens(é a habilitação do CORS)
public class PessoaController {

    @Autowired //o propio springboot vai se engarregar de criar toda injeção de dependencias sem nos precisarmos criar os construtores dessa classe
    private MundialUseCase mundialUseCase;
    @Autowired
    private AnoNascimentoUseCase anoNascimentoUseCase;
    @Autowired
    private ImcUseCase imcUseCase;
    @Autowired
    private ConverteSaldoEmDolarUseCase converteSaldoEmDolarUseCase;
    @Autowired
    private CalculaFaixaImpostoRendaCase calculaFaixaImpostoRendaCase;

    @Autowired
    private MontarRespostaFrontEndUseCase montandoRespostaFrontEnd;


    @GetMapping("/InfoPessoaGet")
    public ResponseEntity<Object> get() {
        PessoaRequest pessoaRequest1 = PessoaRequest.builder().build();
        pessoaRequest1.setNome("Kaio");
        pessoaRequest1.setSobrenome("Vinicius(Aqui estamos setando dados fixos)");
        pessoaRequest1.setEndereco("Rua Isabel Cristina, 869");
        pessoaRequest1.setIdade(16);

        return ResponseEntity.ok(pessoaRequest1);
    }

    @GetMapping("/resumo")
    public ResponseEntity<Object> getPessoa(@RequestBody PessoaRequest pessoinha, @RequestParam(value = "valida_mundial") Boolean DesejaValidarMundial) {

        InformacoesImc imc = InformacoesImc.builder().build();
        String ImpostoRenda = null;
        String validaMundial = null;
        String SaldoEmDolar = null;
        int anoNascimento = 0;

        if (!pessoinha.getNome().isEmpty()) {

            log.info("iniciando o processo de resumo da pessoinha: ", pessoinha);

            if (Objects.nonNull(pessoinha.getPeso()) && Objects.nonNull(pessoinha.getAltura())) {
                log.info("iniciando o calculo do IMC");
                imc = imcUseCase.calculaImc(pessoinha.getPeso(), pessoinha.getAltura());
            }

            if (Objects.nonNull(pessoinha.getIdade())) {
                log.info("Iniciando o calculo de ano de nascimento");
                anoNascimento = anoNascimentoUseCase.calculaAnoNascimento(pessoinha.getIdade());
            }

            if (Objects.nonNull(pessoinha.getSalario())) {
                log.info("Iniciando calculo de imposto de renda");
                ImpostoRenda = calculaFaixaImpostoRendaCase.calculoFaixaImpostoRenda(pessoinha.getSalario());
            }

            if (Boolean.TRUE.equals(DesejaValidarMundial)) { //se o booleano passado la na URl for igual a true...
                if (Objects.nonNull(pessoinha.getTime())) {
                    log.info("Validando se o time de coração tem mundial");
                    validaMundial = mundialUseCase.calculoMundial(pessoinha.getTime());
                }
            }

            if (Objects.nonNull(pessoinha.getSaldo())) {
                log.info("Convertendo real em dolar");
                SaldoEmDolar = converteSaldoEmDolarUseCase.conversaoSaldoEmDolarCase(pessoinha.getSaldo());
            }

            log.info("Montando objeto de retorno para o Front End");
            PessoaResponse resumo = montandoRespostaFrontEnd.montandoRespostaFrontEnd(pessoinha, imc, anoNascimento, ImpostoRenda, validaMundial, SaldoEmDolar);

            return ResponseEntity.ok(resumo);
        }
        return ResponseEntity.noContent().build();
    }
}