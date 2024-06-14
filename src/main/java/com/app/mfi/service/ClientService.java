package com.app.mfi.service;

import com.app.mfi.model.Client;
import com.app.mfi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return Optional.of(clientRepository.getReferenceById(id));
    }

    public Optional<Client> getClientByFirstname(String firstname) {
        return clientRepository.findOneByFirstname(firstname);
    }

    public Client createClient(Client client) {
        try {
            Client _client = clientRepository.save(new Client(client.getFirstname(), client.getLastname(),
                    client.getEmail(),
                    passwordEncoder.encode(client.getPasswordHash())));
            return _client;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Client updateClient(Long id, Client client) {
        Optional<Client> clientData = clientRepository.findById(id);
        if (clientData.isPresent()) {
            Client _client = clientData.get();
            _client.setFirstname(client.getFirstname());
            _client.setLastname(client.getLastname());
            _client.setEmail(client.getEmail());
            _client.setPasswordHash(passwordEncoder.encode(client.getPasswordHash()));
            clientRepository.save(_client);
            return _client;
        } else {
            return null;
        }
    }

    public void deleteClient(Long id) throws Exception {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("not found client");
        }
    }

}
