package com.petclinic.clinica_api.dto;

public record VeterinarioResponseDTO(
        Long id,
        String nome,
        String crmv,
        String especialidade,
        String telefone,
        String email,
        Long clinicaId,
        String nomeClinica
) {}
