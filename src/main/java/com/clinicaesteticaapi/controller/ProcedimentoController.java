package com.clinicaesteticaapi.controller;

import com.clinicaesteticaapi.dto.ProcedimentoRequestDTO;
import com.clinicaesteticaapi.dto.ProcedimentoResponseDTO;
import com.clinicaesteticaapi.service.ProcedimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Procedimento", description = "Gerenciamento de procedimentos")
@RestController
@RequestMapping("/api/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoService service;

    @Operation(summary = "Listar todos os Procedimento")
    @GetMapping
    public List<ProcedimentoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<ProcedimentoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Procedimento por ID")
    @GetMapping("/{id}")
    public ProcedimentoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Procedimento")
    @PostMapping
    public ResponseEntity<ProcedimentoResponseDTO> criar(@Valid @RequestBody ProcedimentoRequestDTO procedimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(procedimento));
    }

    @Operation(summary = "Atualizar Procedimento")
    @PutMapping("/{id}")
    public ProcedimentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProcedimentoRequestDTO procedimento) {
        return service.atualizar(id, procedimento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Procedimento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
