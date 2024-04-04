package com.example.msclients.service;

import com.example.msclients.entity.clients;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface clientService {
    List<clients> list();

    clients save(clients client);

    Optional<clients> findById(Integer id);

    clients update(clients client);

    void delete(Integer id);

}
