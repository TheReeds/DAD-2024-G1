package com.example.mspedido.feign;

import com.example.mspedido.dto.ClienteDto;
import com.example.mspedido.dto.ProductoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="ms-client-service",path = "/clients")
public interface ClienteFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteListarPorIdCB", fallbackMethod = "fallbackClientePorId")
    ResponseEntity<ClienteDto> buscarPorId(@PathVariable(required = true) Integer id);
    default ResponseEntity<ClienteDto> fallbackClientePorId(Integer id, Exception e) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(9000000);
        return ResponseEntity.ok(clienteDto);
    }
}
