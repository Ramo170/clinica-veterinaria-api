package com.petclinic.clinica_api.dto;

public record PacienteResponseDTO(
        Long id,
        String nome,
        String especie,
        String raca,
        Integer idade,
        Long tutorId,
        String nomeTutor
) {}
