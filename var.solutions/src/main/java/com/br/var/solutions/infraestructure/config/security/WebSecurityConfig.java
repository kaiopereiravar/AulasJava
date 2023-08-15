package com.br.var.solutions.infraestructure.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //configurações sempre sobem antes das outras
@EnableWebSecurity //pra falar que é config de segurança
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//extends é a mesma coisa que herdar

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter requestFilter;

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                //não cheque essas requisições
                .authorizeRequests().antMatchers("/pessoa/authorization","/auth", "/pessoa/authenticate", "/configuration/**", "/webjars/**").permitAll()
                //qualquer outra requisição deve ser checada
                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
