package com.petclinic.clinica_api.service;

import com.petclinic.clinica_api.dto.VeterinarioRequestDTO;
import com.petclinic.clinica_api.dto.VeterinarioResponseDTO;
import com.petclinic.clinica_api.model.Clinica;
import com.petclinic.clinica_api.model.Veterinario;
import com.petclinic.clinica_api.repository.ClinicaRepository;
import com.petclinic.clinica_api.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    private final VeterinarioRepository repository;
    private final ClinicaRepository clinicaRepository;

    public VeterinarioService(VeterinarioRepository repository, ClinicaRepository clinicaRepository) {
        this.repository = repository;
        this.clinicaRepository = clinicaRepository;
    }


    public VeterinarioResponseDTO cadastrar(VeterinarioRequestDTO dto) {
        if (repository.existsByCrmv(dto.crmv())) {
            throw new IllegalArgumentException("CRMV já cadastrado para outro veterinário.");
        }

        Clinica clinica = clinicaRepository.findById(dto.clinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada com o ID: " + dto.clinicaId()));

        Veterinario veterinario = new Veterinario();
        veterinario.setNome(dto.nome());
        veterinario.setCrmv(dto.crmv());
        veterinario.setEspecialidade(dto.especialidade());
        veterinario.setTelefone(dto.telefone());
        veterinario.setEmail(dto.email());
        veterinario.setClinica(clinica);

        Veterinario vetSalvo = repository.save(veterinario);
        return mapperParaDTO(vetSalvo);
    }

    public VeterinarioResponseDTO mapperParaDTO(Veterinario vet) {
        return new VeterinarioResponseDTO(
                vet.getId(),
                vet.getNome(),
                vet.getCrmv(),
                vet.getEspecialidade(),
                vet.getTelefone(),
                vet.getEmail(),
                vet.getClinica().getId(),
                vet.getClinica().getNome()
        );
    }
}
