package io.github.danielsbaumann.config;

import io.github.danielsbaumann.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("fulano")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER", "ADMIN");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/usuarios")
                .permitAll()

                .antMatchers("/api/clientes/**")
                .hasAnyRole("USER", "ADMIN")

                .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER", "ADMIN")

                .antMatchers("/api/produtos/**")
                .hasRole("ADMIN")

                //permissão para qualque page do sistema , sem especificação precisa de autenticação
                .anyRequest()
                .authenticated()

                .and()
                .httpBasic();

    }

}
