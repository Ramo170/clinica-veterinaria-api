package com.petclinic.clinica_api.dto;

public record ClinicaRequestDTO (
        String nome,
        String cnpj,
        String telefone,
        String endereco

) {}

