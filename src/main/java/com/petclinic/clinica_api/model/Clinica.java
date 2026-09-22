package com.petclinic.clinica_api.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_clinicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    private String telefone;

    private String endereco;
}
