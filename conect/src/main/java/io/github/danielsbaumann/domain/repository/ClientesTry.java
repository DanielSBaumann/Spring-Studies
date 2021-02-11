package io.github.danielsbaumann.domain.repository;

import io.github.danielsbaumann.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesTry extends JpaRepository<Cliente, Integer> {

    //@Query(value = "SELECT c FROM Cliente c WHERE nome = :nome ") ->com hql
    @Query(value = "SELECT * FROM Cliente WHERE nome = :nome ", nativeQuery = true)//assim com sql nativo
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    Cliente findOneByNomeLike(String nome);

    boolean existsByNome(String nome);

    void deleteById(Integer id);

    @Query(" delete from Cliente c where c.nome =:nome")
//@Modifying
    void deletarPorNome(String nome);
}
