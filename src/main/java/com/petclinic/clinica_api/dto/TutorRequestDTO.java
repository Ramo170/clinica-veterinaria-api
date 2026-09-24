package com.petclinic.clinica_api.dto;

public record TutorRequestDTO(
    String nome,
    String cpf,
    String telefone,
    String email
) {}
