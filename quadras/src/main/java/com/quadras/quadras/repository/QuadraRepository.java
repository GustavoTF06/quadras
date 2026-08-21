package com.quadras.quadras.repository;

import com.quadras.quadras.entity.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuadraRepository extends JpaRepository<Quadra, Long> {

    List<Quadra> findByEstabelecimentoId(Long estabelecimentoId);

    List<Quadra> findByEstabelecimentoIdAndStatus(
            Long estabelecimentoId,
            String status
    );

    List<Quadra> findByCategoriaId(Long categoriaId);
}