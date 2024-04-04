package com.example.msclients.controller;

import com.example.msclients.service.clientService;
import com.example.msclients.entity.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class clientController {
    @Autowired
    private clientService clientService;

    @GetMapping
    ResponseEntity<List<clients>> list() { return ResponseEntity.ok(clientService.list()); }
    @PostMapping
    ResponseEntity<clients> save(@RequestBody clients client){
        return ResponseEntity.ok(clientService.save(client));
    }

    @GetMapping("/{id}")
    ResponseEntity<clients> findById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok(clientService.findById(id).get());
    }

    @PutMapping("/{id}")
    ResponseEntity<clients> update(@PathVariable(required = true) Integer id,
                                    @RequestBody clients client){
        client.setId(id);
        return ResponseEntity.ok(clientService.update(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<clients>> delete(@PathVariable(required = true) Integer id){
        clientService.delete(id);
        return ResponseEntity.ok(clientService.list());
    }

}
