package com.petclinic.clinica_api.controller;

import com.petclinic.clinica_api.dto.VeterinarioRequestDTO;
import com.petclinic.clinica_api.dto.VeterinarioResponseDTO;
import com.petclinic.clinica_api.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService service;

    public VeterinarioController(VeterinarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VeterinarioResponseDTO> cadastrar(@RequestBody VeterinarioRequestDTO dto) {
        VeterinarioResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
