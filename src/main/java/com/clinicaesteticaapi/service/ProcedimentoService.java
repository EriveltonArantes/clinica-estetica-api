package com.clinicaesteticaapi.service;

import com.clinicaesteticaapi.dto.ProcedimentoRequestDTO;
import com.clinicaesteticaapi.dto.ProcedimentoResponseDTO;
import com.clinicaesteticaapi.exception.ResourceNotFoundException;
import com.clinicaesteticaapi.mapper.ProcedimentoMapper;
import com.clinicaesteticaapi.model.Procedimento;
import com.clinicaesteticaapi.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository repository;

    @Autowired
    private ProcedimentoMapper mapper;

    @Transactional(readOnly = true)
    public List<ProcedimentoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProcedimentoResponseDTO buscar(Long id) {
        Procedimento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ProcedimentoResponseDTO criar(ProcedimentoRequestDTO dto) {
        Procedimento entity = mapper.toEntity(dto);
        Procedimento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ProcedimentoResponseDTO atualizar(Long id, ProcedimentoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Procedimento não encontrado com id: " + id);
        }
        Procedimento entity = mapper.toEntity(dto);
        entity.setId(id);
        Procedimento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Procedimento não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
