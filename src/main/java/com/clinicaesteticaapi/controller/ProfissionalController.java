package com.clinicaesteticaapi.controller;

import com.clinicaesteticaapi.dto.ProfissionalRequestDTO;
import com.clinicaesteticaapi.dto.ProfissionalResponseDTO;
import com.clinicaesteticaapi.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Profissional", description = "Gerenciamento de profissionals")
@RestController
@RequestMapping("/api/profissionals")
public class ProfissionalController {

    @Autowired
    private ProfissionalService service;

    @Operation(summary = "Listar todos os Profissional")
    @GetMapping
    public List<ProfissionalResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<ProfissionalResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Profissional por ID")
    @GetMapping("/{id}")
    public ProfissionalResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Profissional")
    @PostMapping
    public ResponseEntity<ProfissionalResponseDTO> criar(@Valid @RequestBody ProfissionalRequestDTO profissional) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(profissional));
    }

    @Operation(summary = "Atualizar Profissional")
    @PutMapping("/{id}")
    public ProfissionalResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProfissionalRequestDTO profissional) {
        return service.atualizar(id, profissional);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Profissional")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
