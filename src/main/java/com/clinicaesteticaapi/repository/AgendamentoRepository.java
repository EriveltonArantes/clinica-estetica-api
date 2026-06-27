package com.clinicaesteticaapi.repository;

import com.clinicaesteticaapi.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
