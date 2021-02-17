package io.github.danielsbaumann.lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;

import static java.util.Comparator.comparing;

public class Teste6 {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Daniel Baumann", 150);
        Usuario user2 = new Usuario("Thomas Baumann", 120);
        Usuario user3 = new Usuario("Paula Bastos", 190);
        Usuario user4 = new Usuario("Antonio Baumann", 220);
        Usuario user5 = new Usuario("Lucas Baumann", 130);
        Usuario user6 = new Usuario("Carlos Bastos", 175);
        List<Usuario> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

        double media = users.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0.0);

        System.out.println("media de pontos -> " + media);

        users.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .ifPresent(valor -> System.out.println("printando valor caso exista -> " + valor));

        users.stream()
                .min(comparing(Usuario::getPontos))
                .ifPresent(value -> System.out.println("Menor valor de pontos" + value));

        int total = users.stream()
                .mapToInt(Usuario::getPontos)
                .sum();

        int total2 = users.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum);

        Iterator<Usuario> i = users.stream().iterator();

        //parei pagina 67 (79 de 140)
    }
}
