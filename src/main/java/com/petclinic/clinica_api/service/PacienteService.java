package com.petclinic.clinica_api.service;

import com.petclinic.clinica_api.dto.PacienteRequestDTO;
import com.petclinic.clinica_api.dto.PacienteResponseDTO;
import com.petclinic.clinica_api.model.Paciente;
import com.petclinic.clinica_api.model.Tutor;
import com.petclinic.clinica_api.repository.PacienteRepository;
import com.petclinic.clinica_api.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final TutorRepository tutorRepository;

    public PacienteService(PacienteRepository repository, TutorRepository tutorRepository) {
        this.repository = repository;
        this.tutorRepository = tutorRepository;
    }


    public PacienteResponseDTO cadastrar(PacienteRequestDTO dto) {
        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + dto.tutorId()));

        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setEspecie(dto.especie());
        paciente.setRaca(dto.raca());
        paciente.setIdade(dto.idade());
        paciente.setTutor(tutor);

        Paciente pacienteSalvo = repository.save(paciente);
        return mapperParaDTO(pacienteSalvo);
    }


    public List<PacienteResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapperParaDTO)
                .toList();
    }


    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));

        return mapperParaDTO(paciente);
    }


    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));

        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + dto.tutorId()));

        paciente.setNome(dto.nome());
        paciente.setEspecie(dto.especie());
        paciente.setRaca(dto.raca());
        paciente.setIdade(dto.idade());
        paciente.setTutor(tutor);

        Paciente pacienteAtualizado = repository.save(paciente);
        return mapperParaDTO(pacienteAtualizado);
    }


    public void deletar(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));

        repository.delete(paciente);
    }

    private PacienteResponseDTO mapperParaDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEspecie(),
                paciente.getRaca(),
                paciente.getIdade(),
                paciente.getTutor().getId(),
                paciente.getTutor().getNome()
        );
    }
}
