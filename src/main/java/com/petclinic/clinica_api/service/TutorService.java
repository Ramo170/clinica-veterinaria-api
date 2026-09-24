package com.petclinic.clinica_api.service;

import com.petclinic.clinica_api.dto.TutorRequestDTO;
import com.petclinic.clinica_api.dto.TutorResponseDTO;
import com.petclinic.clinica_api.model.Tutor;
import com.petclinic.clinica_api.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository){
        this.tutorRepository = tutorRepository;
    }

    public TutorResponseDTO cadastrar(TutorRequestDTO dto){
        if (tutorRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado para outro tutor.");
        }

        Tutor tutor = new Tutor();
        tutor.setNome(dto.nome());
        tutor.setCpf(dto.cpf());
        tutor.setTelefone(dto.telefone());
        tutor.setEmail(dto.email());

        Tutor tutorSalvo = tutorRepository.save(tutor);

        return mapperParaDTO(tutorSalvo);
    }

    public List<TutorResponseDTO> listarTodos(){
        return tutorRepository.findAll()
                .stream()
                .map(this::mapperParaDTO)
                .toList();
    }

    public TutorResponseDTO buscarPorId(Long id){
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encotrado com o ID: " + id));

        return mapperParaDTO(tutor);
    }

    public TutorResponseDTO atualizar(Long id, TutorRequestDTO dto ) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encotrado com o ID: " + id));

        if (!tutor.getCpf().equals(dto.cpf()) && tutorRepository.existsByCpf(dto.cpf())){
            throw new IllegalArgumentException("O novo CPF informado já está em uso por outro tutor");
        }

        tutor.setNome(dto.nome());
        tutor.setCpf(dto.cpf());
        tutor.setTelefone(dto.telefone());
        tutor.setEmail(dto.email());

        Tutor tutorAtualizado = tutorRepository.save(tutor);

        return mapperParaDTO(tutorAtualizado);
    }

    public void deletar(Long id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encotrado com o ID: " + id));

        tutorRepository.delete(tutor);
    }

    public TutorResponseDTO mapperParaDTO(Tutor tutor){
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNome(),
                tutor.getCpf(),
                tutor.getTelefone(),
                tutor.getEmail()
        );
    }
}
