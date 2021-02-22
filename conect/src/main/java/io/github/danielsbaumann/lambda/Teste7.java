package io.github.danielsbaumann.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Teste7 {

    public static void main(String[] args) throws IOException {
//        Usuario user1 = new Usuario("Daniel Baumann", 150);
//        Usuario user2 = new Usuario("Thomas Baumann", 120);
//        Usuario user3 = new Usuario("Paula Bastos", 190);
//        Usuario user4 = new Usuario("Antonio Baumann", 220);
//        Usuario user5 = new Usuario("Lucas Baumann", 130);
//        Usuario user6 = new Usuario("Carlos Bastos", 175);
//        List<Usuario> users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//        users.add(user4);
//        users.add(user5);
//        users.add(user6);

        Files.list(Paths.get("C:\\Users\\USER\\Desktop\\Material de estudo")).forEach(System.out::println);


    }
}
