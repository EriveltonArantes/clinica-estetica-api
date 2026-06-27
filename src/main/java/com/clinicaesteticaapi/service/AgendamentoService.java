package com.clinicaesteticaapi.service;

import com.clinicaesteticaapi.dto.AgendamentoRequestDTO;
import com.clinicaesteticaapi.dto.AgendamentoResponseDTO;
import com.clinicaesteticaapi.exception.ResourceNotFoundException;
import com.clinicaesteticaapi.mapper.AgendamentoMapper;
import com.clinicaesteticaapi.model.Agendamento;
import com.clinicaesteticaapi.repository.AgendamentoRepository;
import com.clinicaesteticaapi.repository.ProfissionalRepository;
import com.clinicaesteticaapi.model.Profissional;
import com.clinicaesteticaapi.repository.ProcedimentoRepository;
import com.clinicaesteticaapi.model.Procedimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private AgendamentoMapper mapper;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @Transactional(readOnly = true)
    public List<AgendamentoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AgendamentoResponseDTO buscar(Long id) {
        Agendamento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public AgendamentoResponseDTO criar(AgendamentoRequestDTO dto) {
        Agendamento entity = mapper.toEntity(dto);
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + dto.getProfissionalId()));
        entity.setProfissional(profissional);
        Procedimento procedimento = procedimentoRepository.findById(dto.getProcedimentoId())
            .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com id: " + dto.getProcedimentoId()));
        entity.setProcedimento(procedimento);
        Agendamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado com id: " + id);
        }
        Agendamento entity = mapper.toEntity(dto);
        entity.setId(id);
        Profissional profissional = profissionalRepository.findById(dto.getProfissionalId())
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + dto.getProfissionalId()));
        entity.setProfissional(profissional);
        Procedimento procedimento = procedimentoRepository.findById(dto.getProcedimentoId())
            .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com id: " + dto.getProcedimentoId()));
        entity.setProcedimento(procedimento);
        Agendamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
