package com.example.msclients.repository;

import com.example.msclients.entity.clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clientRepository extends JpaRepository<clients, Integer> {
}
