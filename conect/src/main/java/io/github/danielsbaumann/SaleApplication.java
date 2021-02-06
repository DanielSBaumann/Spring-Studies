package io.github.danielsbaumann;

import io.github.danielsbaumann.domain.entity.Cliente;
import io.github.danielsbaumann.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class SaleApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {

            /*
            Cliente cliente = new Cliente();
            cliente.setNome("Daniel Baumann");
            clientes.salvar(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Thomas Baumann");
            clientes.salvar(cliente2);
            */

            clientes.salvar(new Cliente("Daniel Baumann"));
            clientes.salvar(new Cliente("Thomas Baumann"));
            clientes.salvar(new Cliente("Paula Bastos"));

            List<Cliente> list = clientes.obterTodos();

            list.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
    }

}
