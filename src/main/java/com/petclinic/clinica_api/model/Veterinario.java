package com.petclinic.clinica_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_veterinarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String crmv;

    private String especialidade;

    private String telefone;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id", nullable = false)
    private Clinica clinica;
}
