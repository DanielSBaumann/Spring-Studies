package io.github.danielsbaumann.lambda;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Teste {

    public static void main(String[] args) {
        Usuario user1 = new Usuario("Daniel Baumann", 150);
        Usuario user2 = new Usuario("Thomas Baumann", 120);
        Usuario user3 = new Usuario("Paula Bastos", 190);

        List<Usuario> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Consumer<Usuario> mostraMensagem = u ->
                System.out.println("antes de imprimir os nomes");
        Consumer<Usuario> imprimeNome = u ->
                System.out.println(u.getNome());

        users.forEach(mostraMensagem.andThen(imprimeNome));

//        Predicate<Usuario> predicado = new Predicate<Usuario>() {
//            public boolean test(Usuario u) {
//                return u.getPontos() > 160;
//            }
//        };

        System.out.println("Apos remove");
        users.removeIf(u -> u.getPontos() > 160);
        users.forEach(u -> System.out.println(u.getNome()));

//        users.forEach(u -> System.out.println(u.getNome()));
//        users.forEach(u -> u.tornarModerador());

//        JButton button = new JButton();
//
//        button.addActionListener(event -> System.out.println());

//        String cep = "22790-685";
//        boolean aux = cep.matches("\\d{5}-\\d{3}");
//        String teste = "teste";
//        Runnable o = () -> {
//            System.out.println(teste);
//        };
//        for (Usuario u : users) {
//            System.out.println(u.getNome());
//        }
    }

}
