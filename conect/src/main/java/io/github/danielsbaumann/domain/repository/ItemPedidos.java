package io.github.danielsbaumann.domain.repository;

import io.github.danielsbaumann.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidos extends JpaRepository<ItemPedido, Integer> {
}
