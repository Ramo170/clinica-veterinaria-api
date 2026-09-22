package com.petclinic.clinica_api.repository;

import com.petclinic.clinica_api.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    boolean existsByCnpj(String cnpj);
}
