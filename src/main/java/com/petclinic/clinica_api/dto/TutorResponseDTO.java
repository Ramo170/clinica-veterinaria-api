package com.petclinic.clinica_api.dto;

public record TutorResponseDTO(
    Long id,
    String nome,
    String cpf,
    String telefone,
    String email
) {}
