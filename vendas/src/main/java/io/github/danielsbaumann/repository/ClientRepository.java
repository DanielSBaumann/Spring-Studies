package io.github.danielsbaumann.repository;

import org.springframework.stereotype.Repository;
import io.github.danielsbaumann.model.Client;

@Repository
public class ClientRepository {
    public void persist(Client client){
        //acessa database e salva cliente
    }
}
