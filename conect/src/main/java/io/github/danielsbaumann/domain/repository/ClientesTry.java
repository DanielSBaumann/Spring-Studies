package io.github.danielsbaumann.domain.repository;

import io.github.danielsbaumann.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesTry extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    Cliente findOneByNomeLike(String nome);

    boolean existsByNome(String nome);
}
