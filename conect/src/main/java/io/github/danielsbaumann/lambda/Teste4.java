package io.github.danielsbaumann.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class Teste4 {
    public static void main(String[] args) {
        //Stream
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

//        users.stream()
//                .filter(u -> u.getPontos() > 150)
//                .forEach(System.out::println);

//        List<Usuario> newUsers = users.stream()
//                .filter(u -> u.getPontos() > 150)
//                .collect(toList());
//        newUsers.forEach(System.out::println);

//        List<Integer> nums = users.sort(comparingInt(Usuario::getPontos)).stream();

//        List<Integer> nums = users.stream()
//                .map(Usuario::getPontos)
//                .collect(toList());

//        IntStream nums = users.stream()
//                .mapToInt(Usuario::getPontos);

//        List<Usuario> newList = users.stream()
//                .filter(u -> u.getPontos() > 150)
//                .sorted(comparing(Usuario::getNome))
//                .collect(toList());

        System.out.println("\nFiltrando usuarios com mais de 150 pontos e printando por ordem alfabetica\n");
        users.stream()
                .filter(user -> user.getPontos() > 150)
                .sorted(comparing(Usuario::getNome))
                .forEach(System.out::println);

//        System.out.println("imprimindo numeros de pontos num List");
//        nums.forEach(System.out::println);

        //obtendo media dos pontos
        double media = users.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0.0);
        System.out.println("Media dos pontos \nMedia -> " + media);
    }
}
