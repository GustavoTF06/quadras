package com.quadras.quadras.service;

import com.quadras.quadras.dto.SolicitacaoEstabelecimentoResponseDTO;
import com.quadras.quadras.entity.Estabelecimento;
import com.quadras.quadras.entity.SolicitacaoEstabelecimento;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.repository.EstabelecimentoRepository;
import com.quadras.quadras.repository.SolicitacaoEstabelecimentoRepository;
import com.quadras.quadras.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoEstabelecimentoService {

    private final SolicitacaoEstabelecimentoRepository solicitacaoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitacaoEstabelecimentoService(
            SolicitacaoEstabelecimentoRepository solicitacaoRepository,
            EstabelecimentoRepository estabelecimentoRepository,
            UsuarioRepository usuarioRepository) {

        this.solicitacaoRepository = solicitacaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<SolicitacaoEstabelecimentoResponseDTO> listarPendentes() {

        return solicitacaoRepository.findByStatus("PENDENTE")
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public SolicitacaoEstabelecimentoResponseDTO aprovar(
            Long solicitacaoId,
            Long administradorId) {

        SolicitacaoEstabelecimento solicitacao =
                solicitacaoRepository.findById(solicitacaoId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Solicitação não encontrada"));

        if (!"PENDENTE".equals(solicitacao.getStatus())) {
            throw new RuntimeException(
                    "Esta solicitação já foi analisada");
        }

        Usuario administrador =
                usuarioRepository.findById(administradorId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Administrador não encontrado"));

        if (!"ADMIN".equals(administrador.getRole())) {
            throw new RuntimeException(
                    "Usuário não possui permissão de administrador");
        }

        Estabelecimento estabelecimento =
                solicitacao.getEstabelecimento();

        solicitacao.setStatus("APROVADO");
        solicitacao.setAdministrador(administrador);
        solicitacao.setDataAnalise(LocalDateTime.now());

        estabelecimento.setStatus("APROVADO");

        estabelecimentoRepository.save(estabelecimento);
        SolicitacaoEstabelecimento salva =
                solicitacaoRepository.save(solicitacao);

        return converterParaResponse(salva);
    }

    public SolicitacaoEstabelecimentoResponseDTO recusar(
            Long solicitacaoId,
            Long administradorId,
            String motivo) {

        SolicitacaoEstabelecimento solicitacao =
                solicitacaoRepository.findById(solicitacaoId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Solicitação não encontrada"));

        if (!"PENDENTE".equals(solicitacao.getStatus())) {
            throw new RuntimeException(
                    "Esta solicitação já foi analisada");
        }

        if (motivo == null || motivo.isBlank()) {
            throw new RuntimeException(
                    "O motivo da recusa é obrigatório");
        }

        Usuario administrador =
                usuarioRepository.findById(administradorId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Administrador não encontrado"));

        if (!"ADMIN".equals(administrador.getRole())) {
            throw new RuntimeException(
                    "Usuário não possui permissão de administrador");
        }

        Estabelecimento estabelecimento =
                solicitacao.getEstabelecimento();

        solicitacao.setStatus("RECUSADO");
        solicitacao.setAdministrador(administrador);
        solicitacao.setMotivoRecusa(motivo);
        solicitacao.setDataAnalise(LocalDateTime.now());

        estabelecimento.setStatus("RECUSADO");

        estabelecimentoRepository.save(estabelecimento);
        SolicitacaoEstabelecimento salva =
                solicitacaoRepository.save(solicitacao);

        return converterParaResponse(salva);
    }

    private SolicitacaoEstabelecimentoResponseDTO converterParaResponse(
            SolicitacaoEstabelecimento solicitacao) {

        Long administradorId = null;

        if (solicitacao.getAdministrador() != null) {
            administradorId =
                    solicitacao.getAdministrador().getUsuarioId();
        }

        return new SolicitacaoEstabelecimentoResponseDTO(
                solicitacao.getId(),
                solicitacao.getEstabelecimento().getId(),
                solicitacao.getUsuarioSolicitante().getUsuarioId(),
                administradorId,
                solicitacao.getStatus(),
                solicitacao.getMotivoRecusa(),
                solicitacao.getDataSolicitacao(),
                solicitacao.getDataAnalise()
        );
    }

    public List<SolicitacaoEstabelecimentoResponseDTO> listarMinhasSolicitacoes(
            Long usuarioId) {

        return solicitacaoRepository
                .findByUsuarioSolicitanteUsuarioId(usuarioId)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }
}