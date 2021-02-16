package io.github.danielsbaumann.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class Teste2 {

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

        users.forEach(u -> System.out.println(u.getNome()));

        //Collections.sort(users, Comparator.comparing(Usuario::getNome));

        //users.sort(Comparator.comparing(Usuario::getPontos));

        users.sort(comparing(u -> u.getNome()));
//        Function<Usuario, String> byName = Usuario::getNome;
//        users.sort(comparing(byName)); muito mais legivel e enxuto

        System.out.println("\nReordenando por nome");
        //users.sort(comparing(Usuario::getNome)); forma mais enxuta

        users.forEach(u -> System.out.println(u.getNome()));

        users.sort(comparingInt(u -> u.getPontos()));
        //users.sort(comparing(Usuario::getPontos).reversed()); mais enxuto , e ordenado decrescente
        System.out.println("\nReordenando por pontos");
        users.forEach(u -> System.out.println(u.getPontos()));

        users.forEach(Usuario::tornarModerador);
    }

}
