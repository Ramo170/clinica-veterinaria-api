package com.petclinic.clinica_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(length = 50)
    private String raca;

    private Integer idade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;
}
