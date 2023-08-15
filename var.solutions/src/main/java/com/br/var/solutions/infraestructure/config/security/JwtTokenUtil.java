package com.br.var.solutions.infraestructure.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_EXPIRES = 180000; // 30 minutos

    public String secret = "57c304c9793a3047c46dd8c0f9691694cd11e12ce138424a28631affc67c27f439fe8e9fe152e913bb79769566b58d306288d5341c3a163b2ff66bc5457f9b49";

    //retorna o username do token do jwt
    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retorna varios objetos possiveis de varios tipos possiveis. - captura as informações de dentro do token.
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =  getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //retorna expiration date do token jwt
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //Para retornar qualquer informação do token, e pra isso nós vamos precisar da secret.
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //validar se o token é expirado.
    private Boolean isTokenExpires(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Gerar Token - apenas chama o "doGenerateToken"
    public String generateToken(String clientId){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, clientId);
    }

    //Cria o token e também vai definir o tempo de expiração.
    private String doGenerateToken(Map<String, Object> claims, String clientId) {
        return Jwts.builder().setClaims(claims).setSubject(clientId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRES))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validar o token - se a data não esta espirada e se é o usuario de fato
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpires(token);
    }
}
