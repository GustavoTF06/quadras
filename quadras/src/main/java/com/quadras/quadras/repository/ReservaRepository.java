package com.quadras.quadras.repository;

import com.quadras.quadras.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

    List<Reserva> findByQuadraIdAndDataInicioLessThanAndDataFimGreaterThan(
            Long quadraId,
            LocalDateTime dataFim,
            LocalDateTime dataInicio
    );

    List<Reserva> findByUsuarioUsuarioId(Long usuarioId);

    List<Reserva> findByQuadraId(Long quadraId);

}