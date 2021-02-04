package io.github.danielsbaumann;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean(name = "cachorro")
    public Animal cachorro(){
        return new Animal(){
            @Override
            public void makeNoise(){
                System.out.println("Au Au");
            }
        };
    }

    @Bean(name = "gato")
    public Animal gato(){
        return new Animal(){
            @Override
            public void makeNoise(){
                System.out.println("Miau");
            }
        };
    }
}
