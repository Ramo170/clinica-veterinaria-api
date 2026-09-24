package com.petclinic.clinica_api.controller;

import com.petclinic.clinica_api.dto.PacienteRequestDTO;
import com.petclinic.clinica_api.dto.PacienteResponseDTO;
import com.petclinic.clinica_api.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    // RF06 - Cadastrar paciente
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(@RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // RF07 - Listar pacientes
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<PacienteResponseDTO> pacientes = service.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    // RF08 - Consultar paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        PacienteResponseDTO paciente = service.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    // RF09 - Modificar cadastro de paciente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    // RF10 - Excluir paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
