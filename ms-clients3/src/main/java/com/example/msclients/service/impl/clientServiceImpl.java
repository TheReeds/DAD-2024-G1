package com.example.msclients.service.impl;

import com.example.msclients.repository.clientRepository;
import com.example.msclients.entity.clients;
import com.example.msclients.service.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class clientServiceImpl implements clientService {
    @Autowired
    private clientRepository clientRepository;

    @Override
    public List<clients> list() {
        return clientRepository.findAll();
    }

    @Override
    public clients save(clients client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<clients> findById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public clients update(clients client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }
}
