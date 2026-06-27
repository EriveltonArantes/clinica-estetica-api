package com.clinicaesteticaapi.mapper;

import com.clinicaesteticaapi.dto.PacoteRequestDTO;
import com.clinicaesteticaapi.dto.PacoteResponseDTO;
import com.clinicaesteticaapi.model.Pacote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacoteMapper {

    @Mapping(target = "procedimentos", ignore = true)
    Pacote toEntity(PacoteRequestDTO dto);

    @Mapping(target = "procedimentosId", source = "procedimentos.id")
    PacoteResponseDTO toResponseDTO(Pacote entity);
}
