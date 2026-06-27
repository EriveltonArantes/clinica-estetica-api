package com.clinicaesteticaapi.repository;

import com.clinicaesteticaapi.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {
}
