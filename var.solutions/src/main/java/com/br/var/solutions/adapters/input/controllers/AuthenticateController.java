package com.br.var.solutions.adapters.input.controllers;

import com.br.var.solutions.adapters.input.entities.GenerateToken;
import com.br.var.solutions.application.services.entities.ValidaUsuario;
import com.br.var.solutions.infraestructure.config.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")//posso receber chamadas de todas origens(é a habilitação do CORS)
public class AuthenticateController {

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<GenerateToken> generateToken(@RequestParam("cliente_id") String clienteId,
                                                       @RequestParam("password") String password) {

        log.info("iniciando a tentativa de geração de token para o usuario: "+ clienteId);

        Boolean validaUsuario = ValidaUsuario.validaUsuario(clienteId, password);

        if (Boolean.FALSE.equals(validaUsuario)){
            log.error("Não foi possivel gerar o token pois o usuario ou senha estão incorretos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenerateToken.builder().build());
        }

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generateToken(clienteId);

        GenerateToken tokenResponse = GenerateToken.builder().build();
        tokenResponse.setToken(token);

        log.info("token gerado com sucesso para o usuario: " + clienteId + "Em: " + System.currentTimeMillis());
        return ResponseEntity.ok(tokenResponse);
    }
}
