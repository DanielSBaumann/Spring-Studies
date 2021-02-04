package io.github.danielsbaumann;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SaleApplication {

    /*
    assim foi utilizado antes do arquivo "resources.applicationproperties"
    @Autowired
    @Qualifier("applicationName")
     */

    @Value("${application.name}")
    private String applicationName;

    //injetando animal aqui
    //@Autowired
    /*@Qualifier("gato") dessa forma caso não exista anotation costumizada*/
    @Cachorro
    private Animal animal;

    @Bean(name = "executeAnimal")
    public CommandLineRunner exe(){
        return args ->{
            this.animal.makeNoise();
        };
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
    }
}
