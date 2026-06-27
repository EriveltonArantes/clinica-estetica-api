package com.clinicaesteticaapi.controller;

import com.clinicaesteticaapi.dto.PacoteRequestDTO;
import com.clinicaesteticaapi.dto.PacoteResponseDTO;
import com.clinicaesteticaapi.service.PacoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Pacote", description = "Gerenciamento de pacotes")
@RestController
@RequestMapping("/api/pacotes")
public class PacoteController {

    @Autowired
    private PacoteService service;

    @Operation(summary = "Listar todos os Pacote")
    @GetMapping
    public List<PacoteResponseDTO> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) Long procedimentosId) {
        List<PacoteResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (procedimentosId != null) {
            resultado = resultado.stream().filter(item -> procedimentosId.equals(item.getProcedimentosId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Pacote por ID")
    @GetMapping("/{id}")
    public PacoteResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Pacote")
    @PostMapping
    public ResponseEntity<PacoteResponseDTO> criar(@Valid @RequestBody PacoteRequestDTO pacote) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(pacote));
    }

    @Operation(summary = "Atualizar Pacote")
    @PutMapping("/{id}")
    public PacoteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PacoteRequestDTO pacote) {
        return service.atualizar(id, pacote);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Pacote")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
