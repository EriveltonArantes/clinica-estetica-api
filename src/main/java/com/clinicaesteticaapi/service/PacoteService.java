package com.clinicaesteticaapi.service;

import com.clinicaesteticaapi.dto.PacoteRequestDTO;
import com.clinicaesteticaapi.dto.PacoteResponseDTO;
import com.clinicaesteticaapi.exception.ResourceNotFoundException;
import com.clinicaesteticaapi.mapper.PacoteMapper;
import com.clinicaesteticaapi.model.Pacote;
import com.clinicaesteticaapi.repository.PacoteRepository;
import com.clinicaesteticaapi.repository.ProcedimentoRepository;
import com.clinicaesteticaapi.model.Procedimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacoteService {

    @Autowired
    private PacoteRepository repository;

    @Autowired
    private PacoteMapper mapper;

    @Autowired
    private ProcedimentoRepository procedimentosRepository;

    @Transactional(readOnly = true)
    public List<PacoteResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacoteResponseDTO buscar(Long id) {
        Pacote entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public PacoteResponseDTO criar(PacoteRequestDTO dto) {
        Pacote entity = mapper.toEntity(dto);
        Procedimento procedimentos = procedimentosRepository.findById(dto.getProcedimentosId())
            .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com id: " + dto.getProcedimentosId()));
        entity.setProcedimentos(procedimentos);
        Pacote salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public PacoteResponseDTO atualizar(Long id, PacoteRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Pacote não encontrado com id: " + id);
        }
        Pacote entity = mapper.toEntity(dto);
        entity.setId(id);
        Procedimento procedimentos = procedimentosRepository.findById(dto.getProcedimentosId())
            .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com id: " + dto.getProcedimentosId()));
        entity.setProcedimentos(procedimentos);
        Pacote salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Pacote não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
