package io.github.danielsbaumann;

import io.github.danielsbaumann.domain.entity.Cliente;
import io.github.danielsbaumann.domain.repository.Clientes;
import io.github.danielsbaumann.domain.repository.ClientesTry;
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
    public CommandLineRunner init(@Autowired ClientesTry clientes) {
        return args -> {

            /*
            Cliente cliente = new Cliente();
            cliente.setNome("Daniel Baumann");
            clientes.salvar(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Thomas Baumann");
            clientes.salvar(cliente2);
            */

            System.out.println("Salvando clientes");

            clientes.save(new Cliente("Daniel Baumann"));
            clientes.save(new Cliente("Thomas Baumann"));
            clientes.save(new Cliente("Paula Bastos"));

            List<Cliente> list = clientes.findAll();

            System.out.println("Print todos clientes");

            list.forEach(System.out::println);

            System.out.println("Atualizando clientes");

            list.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.save(c);
            });

            list.forEach(System.out::println);

            List<Cliente> porNome = clientes.encontrarPorNome("Thomas Baumann atualizado");
            System.out.println("Procurando nome");
            porNome.forEach(System.out::println);

            System.out.println("Deletando pelo id 3");
            clientes.deleteById(3);
            list = clientes.findAll();
            list.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
    }

}
