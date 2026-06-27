package com.clinicaesteticaapi.service;

import com.clinicaesteticaapi.dto.ProfissionalRequestDTO;
import com.clinicaesteticaapi.dto.ProfissionalResponseDTO;
import com.clinicaesteticaapi.exception.ResourceNotFoundException;
import com.clinicaesteticaapi.mapper.ProfissionalMapper;
import com.clinicaesteticaapi.model.Profissional;
import com.clinicaesteticaapi.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repository;

    @Autowired
    private ProfissionalMapper mapper;

    @Transactional(readOnly = true)
    public List<ProfissionalResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfissionalResponseDTO buscar(Long id) {
        Profissional entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ProfissionalResponseDTO criar(ProfissionalRequestDTO dto) {
        Profissional entity = mapper.toEntity(dto);
        Profissional salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ProfissionalResponseDTO atualizar(Long id, ProfissionalRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Profissional não encontrado com id: " + id);
        }
        Profissional entity = mapper.toEntity(dto);
        entity.setId(id);
        Profissional salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Profissional não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
