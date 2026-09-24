package com.petclinic.clinica_api.dto;

public record VeterinarioRequestDTO(
        String nome,
        String crmv,
        String especialidade,
        String telefone,
        String email,
        Long clinicaId
) {}
