package br.com.edu.zup.ecommerce.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    //configuracoes de autorizacao = url perfis de acesso
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // Usar essa configurações somente no desenvolvimento!
        http.authorizeRequests().antMatchers("/**").permitAll().and().authorizeRequests().antMatchers("/console/**").permitAll()
                .and().csrf().disable();
        http.headers().frameOptions().disable();
    }
}
