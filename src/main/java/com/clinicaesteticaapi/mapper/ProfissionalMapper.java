package com.clinicaesteticaapi.mapper;

import com.clinicaesteticaapi.dto.ProfissionalRequestDTO;
import com.clinicaesteticaapi.dto.ProfissionalResponseDTO;
import com.clinicaesteticaapi.model.Profissional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {

    Profissional toEntity(ProfissionalRequestDTO dto);

    ProfissionalResponseDTO toResponseDTO(Profissional entity);
}
