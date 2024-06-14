package com.app.mfi.controller;

import com.app.mfi.dto.ClientDto;
import com.app.mfi.model.Client;
import com.app.mfi.service.ClientService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost",
"http://localhost:5173"})
@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/clients")
    @ResponseBody
    public ResponseEntity<List<ClientDto>> getAllClients() {
        try {
            List<Client> clients = new ArrayList<Client>();

            clients.addAll(clientService.getAllClients());
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<ClientDto> clientDtos = clients.stream().map(this::convertToDto).toList();
            return ResponseEntity.ok(clientDtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/clients/{id}")
    @ResponseBody
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        Optional<Client> clientData = clientService.getClientById(id);
        return clientData.map(client -> ResponseEntity.ok(convertToDto(client))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/clients")
    @ResponseBody
    public ResponseEntity<ClientDto> createClient(@RequestBody Client client) {
        try {
            Client _client = clientService.createClient(client);
            return new ResponseEntity<>(convertToDto(_client), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/clients/{id}")
    @ResponseBody
    private ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Optional<Client> clientData = clientService.getClientById(id);
        if (clientData.isPresent()) {
            Client _client = clientService.updateClient(id, client);
            return ResponseEntity.ok(convertToDto(_client));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clients/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private ClientDto convertToDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    private Client convertToEntity(ClientDto clientDto) throws ParseException {
        return modelMapper.map(clientDto, Client.class);
    }
}
