package com.quadras.quadras.repository;

import com.quadras.quadras.entity.SolicitacaoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoEstabelecimentoRepository
        extends JpaRepository<SolicitacaoEstabelecimento, Long> {


    List<SolicitacaoEstabelecimento> findByStatus(String status);

    List<SolicitacaoEstabelecimento> findByUsuarioSolicitanteUsuarioId(Long usuarioId);

    Optional<SolicitacaoEstabelecimento>
    findByEstabelecimentoIdAndStatus(
            Long estabelecimentoId,
            String status
    );
}