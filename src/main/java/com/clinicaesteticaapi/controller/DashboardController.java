package com.clinicaesteticaapi.controller;

import com.clinicaesteticaapi.model.Profissional;
import com.clinicaesteticaapi.model.Procedimento;
import com.clinicaesteticaapi.model.Agendamento;
import com.clinicaesteticaapi.model.Pacote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.clinicaesteticaapi.repository.ProfissionalRepository profissionalRepository;

    @Autowired
    private com.clinicaesteticaapi.repository.ProcedimentoRepository procedimentoRepository;

    @Autowired
    private com.clinicaesteticaapi.repository.AgendamentoRepository agendamentoRepository;

    @Autowired
    private com.clinicaesteticaapi.repository.PacoteRepository pacoteRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalProfissional", profissionalRepository.count());
        resumo.put("totalProcedimento", procedimentoRepository.count());
        resumo.put("somaPrecoProcedimento", procedimentoRepository.findAll().stream().map(e -> e.getPreco()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("totalAgendamento", agendamentoRepository.count());
        resumo.put("somaValorAgendamento", agendamentoRepository.findAll().stream().map(e -> e.getValor()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("graficoAgendamento", agendamentoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalPacote", pacoteRepository.count());
        resumo.put("somaPrecoPacote", pacoteRepository.findAll().stream().map(e -> e.getPreco()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        return resumo;
    }
}
