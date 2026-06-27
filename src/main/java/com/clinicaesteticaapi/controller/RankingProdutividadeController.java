package com.clinicaesteticaapi.controller;

import com.clinicaesteticaapi.model.Agendamento;
import com.clinicaesteticaapi.model.Profissional;
import com.clinicaesteticaapi.repository.AgendamentoRepository;
import com.clinicaesteticaapi.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/relatorio")
public class RankingProdutividadeController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping("/ranking-produtividade")
    public List<Map<String, Object>> ranking() {
        List<Agendamento> todos = agendamentoRepository.findAll();
        Map<Long, List<Agendamento>> porProfissional = todos.stream()
            .filter(r -> r.getProfissional() != null)
            .collect(Collectors.groupingBy(r -> r.getProfissional().getId()));
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map.Entry<Long, List<Agendamento>> e : porProfissional.entrySet()) {
            long total = e.getValue().size();
            long concluidos = e.getValue().stream()
                .filter(r -> r.getStatus() != null && r.getStatus().toLowerCase().matches(".*conclu.*|.*finaliz.*|.*entreg.*"))
                .count();
            Map<String, Object> linha = new LinkedHashMap<>();
            linha.put("profissionalId", e.getKey());
            linha.put("total", total);
            linha.put("concluidos", concluidos);
            linha.put("percentualConcluido", total == 0 ? 0 : (concluidos * 100.0 / total));
            resultado.add(linha);
        }
        resultado.sort((a, b) -> Long.compare((long) b.get("total"), (long) a.get("total")));
        return resultado;
    }
}
