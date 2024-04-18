package com.example.mspedido.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Integer id;
    private String name;
    private String adress;
    private String age;
    private String email;
    private String gender;
}