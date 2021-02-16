package io.github.danielsbaumann.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

public class Teste3 {
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

        Usuario rodrigo = new Usuario("Rodrigo Turini", 50);

        //Runnable bloco1 = rodrigo::tornarModerador;
        //Runnable bloco2 = () -> rodrigo.tornarModerador();
        users.sort(comparing(Usuario::getNome));
        users.forEach(System.out::println);

        System.out.println("\nRe-imprimindo por ordem de pontos");

        users.sort(comparingInt(Usuario::getPontos).reversed().thenComparing(Usuario::getNome));
        users.forEach(System.out::println);
        //parei pagina 31 ou (51 de 140)

        System.out.println("\nTornando todos moderadores ->");
        users.forEach(Usuario::tornarModerador);
        users.forEach(System.out::println);

        users.add(new Usuario("", 0));
        users.sort(nullsFirst(comparingInt(Usuario::getPontos)));
        users.forEach(System.out::println);

        users.removeIf(u -> u.getPontos() > 200);
        users.forEach(System.out::println);
    }
}
