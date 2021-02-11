package io.github.danielsbaumann.domain.repository;

import io.github.danielsbaumann.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido,Integer> {
}
