package com.petclinic.clinica_api.service;

import com.petclinic.clinica_api.dto.VeterinarioRequestDTO;
import com.petclinic.clinica_api.dto.VeterinarioResponseDTO;
import com.petclinic.clinica_api.model.Clinica;
import com.petclinic.clinica_api.model.Veterinario;
import com.petclinic.clinica_api.repository.ClinicaRepository;
import com.petclinic.clinica_api.repository.VeterinarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<VeterinarioResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapperParaDTO)
                .toList();
    }

    public VeterinarioResponseDTO buscarPorId(Long id) {
        Veterinario vet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado com o ID: " + id));
        return mapperParaDTO(vet);
    }

    public VeterinarioResponseDTO atualizar(Long id, VeterinarioRequestDTO dto) {
        Veterinario vet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado com o ID: " + id));


        if (!vet.getCrmv().equalsIgnoreCase(dto.crmv()) && repository.existsByCrmv(dto.crmv())) {
            throw new IllegalArgumentException("CRMV já cadastrado para outro veterinário.");
        }

        Clinica clinica = clinicaRepository.findById(dto.clinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada com o ID: " + dto.clinicaId()));

        vet.setNome(dto.nome());
        vet.setCrmv(dto.crmv());
        vet.setEspecialidade(dto.especialidade());
        vet.setTelefone(dto.telefone());
        vet.setEmail(dto.email());
        vet.setClinica(clinica);

        Veterinario vetAtualizado = repository.save(vet);
        return mapperParaDTO(vetAtualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Veterinário não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
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
