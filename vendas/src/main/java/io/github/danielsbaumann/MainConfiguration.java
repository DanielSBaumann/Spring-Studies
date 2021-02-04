package io.github.danielsbaumann;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//antes da anotation personalizada
/*@Configuration
@Profile("development")*/
//anotation personalizada

@Development
public class MainConfiguration {

    @Bean
    public CommandLineRunner exe(){
        return args ->{
            System.out.println("RODANDO CONFIGURACAO DE DESENVOLVIMENTO");
        };
    }

    /*
    @Bean(name = "applicationName")
    public String applicationName(){
        return "Sale System";
    }
     */
}
