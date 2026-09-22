package com.petclinic.clinica_api.dto;

public record ClinicaResponseDTO (
        Long id,
        String nome,
        String cnpj,
        String telefone,
        String endereco
) {}
