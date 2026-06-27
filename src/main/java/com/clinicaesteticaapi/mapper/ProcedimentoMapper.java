package com.clinicaesteticaapi.mapper;

import com.clinicaesteticaapi.dto.ProcedimentoRequestDTO;
import com.clinicaesteticaapi.dto.ProcedimentoResponseDTO;
import com.clinicaesteticaapi.model.Procedimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProcedimentoMapper {

    Procedimento toEntity(ProcedimentoRequestDTO dto);

    ProcedimentoResponseDTO toResponseDTO(Procedimento entity);
}
