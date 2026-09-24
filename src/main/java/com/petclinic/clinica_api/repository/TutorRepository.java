package com.petclinic.clinica_api.repository;

import com.petclinic.clinica_api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByCpf(String cpf);
}
