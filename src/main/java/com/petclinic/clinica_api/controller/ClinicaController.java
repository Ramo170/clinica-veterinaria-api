package com.petclinic.clinica_api.controller;

import com.petclinic.clinica_api.dto.ClinicaRequestDTO;
import com.petclinic.clinica_api.dto.ClinicaResponseDTO;
import com.petclinic.clinica_api.service.ClinicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicas")
public class ClinicaController {

    private final ClinicaService service;

    public ClinicaController(ClinicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClinicaResponseDTO> cadastrar(@RequestBody ClinicaRequestDTO dto) {
        ClinicaResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClinicaResponseDTO>> listarTodas() {
        List<ClinicaResponseDTO> clinicas = service.listarTodas();
        return ResponseEntity.ok(clinicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaResponseDTO> buscarPorId(@PathVariable Long id){
        ClinicaResponseDTO clinica = service.buscarPorId(id);
        return ResponseEntity.ok(clinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClinicaRequestDTO dto){
        ClinicaResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
