package com.clinicaesteticaapi.controller;

import com.clinicaesteticaapi.dto.AgendamentoRequestDTO;
import com.clinicaesteticaapi.dto.AgendamentoResponseDTO;
import com.clinicaesteticaapi.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Agendamento", description = "Gerenciamento de agendamentos")
@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @Operation(summary = "Listar todos os Agendamento")
    @GetMapping
    public List<AgendamentoResponseDTO> listar(@RequestParam(required = false) String nomeCliente, @RequestParam(required = false) Long profissionalId, @RequestParam(required = false) Long procedimentoId) {
        List<AgendamentoResponseDTO> resultado = service.listar();
        if (nomeCliente != null && !nomeCliente.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNomeCliente() != null &&
                item.getNomeCliente().toLowerCase().contains(nomeCliente.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (profissionalId != null) {
            resultado = resultado.stream().filter(item -> profissionalId.equals(item.getProfissionalId())).collect(java.util.stream.Collectors.toList());
        }
        if (procedimentoId != null) {
            resultado = resultado.stream().filter(item -> procedimentoId.equals(item.getProcedimentoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Agendamento por ID")
    @GetMapping("/{id}")
    public AgendamentoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Agendamento")
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar(@Valid @RequestBody AgendamentoRequestDTO agendamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(agendamento));
    }

    @Operation(summary = "Atualizar Agendamento")
    @PutMapping("/{id}")
    public AgendamentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AgendamentoRequestDTO agendamento) {
        return service.atualizar(id, agendamento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
