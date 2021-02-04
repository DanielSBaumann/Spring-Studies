package io.github.danielsbaumann.service;

import io.github.danielsbaumann.model.Client;
import io.github.danielsbaumann.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public void saveClient(Client client) {
        validateClient(client);
        this.repository.persist(client);
    }

    public void validateClient(Client client) {
        //aplica validacoes

    }
}
