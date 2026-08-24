package com.quadras.quadras.repository;

import com.quadras.quadras.entity.HorarioQuadra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioQuadraRepository
        extends JpaRepository<HorarioQuadra, Long> {

    List<HorarioQuadra> findByQuadraId(Long quadraId);

    List<HorarioQuadra> findByQuadraIdAndDiaSemana(
            Long quadraId,
            String diaSemana
    );
}