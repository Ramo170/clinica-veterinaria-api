package com.petclinic.clinica_api.service;

import com.petclinic.clinica_api.dto.ClinicaRequestDTO;
import com.petclinic.clinica_api.dto.ClinicaResponseDTO;
import com.petclinic.clinica_api.model.Clinica;
import com.petclinic.clinica_api.repository.ClinicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaService {

    private final ClinicaRepository repository;

    public ClinicaService(ClinicaRepository repository) {
        this.repository = repository;
    }

    public ClinicaResponseDTO cadastrar(ClinicaRequestDTO dto) {
        if (repository.existsByCnpj(dto.cnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado no sistema.");
        }

        Clinica clinica = new Clinica();
        clinica.setNome(dto.nome());
        clinica.setCnpj(dto.cnpj());
        clinica.setTelefone(dto.telefone());
        clinica.setEndereco(dto.endereco());

        Clinica clinicaSalva = repository.save(clinica);

        return mapperParaDTO(clinicaSalva);
    }

    public List<ClinicaResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::mapperParaDTO)
                .toList();
    }

    private ClinicaResponseDTO mapperParaDTO(Clinica clinica) {
        return new ClinicaResponseDTO(
                clinica.getId(),
                clinica.getNome(),
                clinica.getCnpj(),
                clinica.getTelefone(),
                clinica.getEndereco()
        );
    }
}
