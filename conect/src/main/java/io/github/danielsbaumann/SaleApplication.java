package io.github.danielsbaumann;

import io.github.danielsbaumann.domain.entity.Cliente;
import io.github.danielsbaumann.domain.entity.Pedido;
import io.github.danielsbaumann.domain.repository.Clientes;
import io.github.danielsbaumann.domain.repository.ClientesTry;
import io.github.danielsbaumann.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class SaleApplication {

//    @Bean
//    public CommandLineRunner init(
//            @Autowired ClientesTry clientes,
//            @Autowired Pedidos pedidos
//    ) {
//        return args -> {
//
//            System.out.println("Salvando clientes");
//
//            clientes.save(new Cliente("Daniel Baumann"));
//            clientes.save(new Cliente("Thomas Baumann"));
//            clientes.save(new Cliente("Paula Bastos"));
//
//            List<Cliente> list = clientes.findAll();
//
//            System.out.println("Print todos clientes");
//
//            list.forEach(System.out::println);
//
//            Pedido p = new Pedido();
//            p.setCliente(list.get(0));
//            p.setDataPedido(LocalDate.now());
//            p.setTotal(BigDecimal.valueOf(1000));
//
//            pedidos.save(p);

//            Cliente aux = clientes.findClienteFetchPedidos(list.get(0).getId());
//            System.out.println(aux);
//            System.out.println(aux.getPedidos());

//            pedidos.findByCliente((list.get(0))).forEach(System.out::println);

//            System.out.println("Atualizando clientes");
//
//            list.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clientes.save(c);
//            });
//
//            list.forEach(System.out::println);
//
//            List<Cliente> porNome = clientes.encontrarPorNome("Thomas Baumann atualizado");
//            System.out.println("Procurando nome");
//            porNome.forEach(System.out::println);
//
//            System.out.println("Deletando pelo id 3");
//            clientes.deleteById(3);
//            list = clientes.findAll();
//            list.forEach(System.out::println);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
    }

}
