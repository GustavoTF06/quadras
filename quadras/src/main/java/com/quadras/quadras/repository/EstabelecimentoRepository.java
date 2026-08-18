package com.quadras.quadras.repository;

import com.quadras.quadras.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstabelecimentoRepository
        extends JpaRepository<Estabelecimento, Long> {

    List<Estabelecimento> findByUsuarioUsuarioId(Long usuarioId);

    List<Estabelecimento> findByStatus(String status);
}