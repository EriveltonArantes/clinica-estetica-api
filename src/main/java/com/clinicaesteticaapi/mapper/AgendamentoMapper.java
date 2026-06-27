package com.clinicaesteticaapi.mapper;

import com.clinicaesteticaapi.dto.AgendamentoRequestDTO;
import com.clinicaesteticaapi.dto.AgendamentoResponseDTO;
import com.clinicaesteticaapi.model.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    @Mapping(target = "profissional", ignore = true)
    @Mapping(target = "procedimento", ignore = true)
    Agendamento toEntity(AgendamentoRequestDTO dto);

    @Mapping(target = "profissionalId", source = "profissional.id")
    @Mapping(target = "procedimentoId", source = "procedimento.id")
    AgendamentoResponseDTO toResponseDTO(Agendamento entity);
}
