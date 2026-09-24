package com.petclinic.clinica_api.dto;

public record PacienteRequestDTO(
        String nome,
        String especie,
        String raca,
        Integer idade,
        Long tutorId
) {}
