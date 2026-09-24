package com.petclinic.clinica_api.repository;

import com.petclinic.clinica_api.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    boolean existsByCrmv(String crmv);
}
