package io.github.danielsbaumann.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100, unique = true)
    private String nome;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
