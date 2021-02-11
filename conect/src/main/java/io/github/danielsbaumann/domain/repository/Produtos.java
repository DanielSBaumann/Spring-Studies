package io.github.danielsbaumann.domain.repository;

import io.github.danielsbaumann.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
