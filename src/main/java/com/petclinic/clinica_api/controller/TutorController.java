package com.petclinic.clinica_api.controller;

import com.petclinic.clinica_api.dto.TutorRequestDTO;
import com.petclinic.clinica_api.dto.TutorResponseDTO;
import com.petclinic.clinica_api.service.TutorService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    private final TutorService service;

    public TutorController(TutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TutorResponseDTO> cadastrarTutor(@RequestBody TutorRequestDTO dto){
        TutorResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> listarTodos(){
        List<TutorResponseDTO> tutores = service.listarTodos();
        return ResponseEntity.ok(tutores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> buscarPorId(@PathVariable Long id){
        TutorResponseDTO tutor = service.buscarPorId(id);
        return  ResponseEntity.ok(tutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> atualizar(@PathVariable Long id, @RequestBody TutorRequestDTO dto) {
        TutorResponseDTO response = service.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
