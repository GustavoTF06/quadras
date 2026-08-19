package com.quadras.quadras.controller;

import com.quadras.quadras.dto.SolicitacaoEstabelecimentoResponseDTO;
import com.quadras.quadras.service.SolicitacaoEstabelecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes-estabelecimento")
public class SolicitacaoEstabelecimentoController {

    private final SolicitacaoEstabelecimentoService solicitacaoService;

    public SolicitacaoEstabelecimentoController(
            SolicitacaoEstabelecimentoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PutMapping("/{id}/aprovar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SolicitacaoEstabelecimentoResponseDTO> aprovar(
            @PathVariable Long id,
            Authentication authentication) {

        Long administradorId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        return ResponseEntity.ok(
                solicitacaoService.aprovar(id, administradorId)
        );
    }

    @PutMapping("/{id}/recusar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SolicitacaoEstabelecimentoResponseDTO> recusar(
            @PathVariable Long id,
            @RequestBody String motivo,
            Authentication authentication) {

        Long administradorId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        return ResponseEntity.ok(
                solicitacaoService.recusar(
                        id,
                        administradorId,
                        motivo
                )
        );
    }

    @GetMapping("/pendentes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SolicitacaoEstabelecimentoResponseDTO>> listarPendentes() {

        return ResponseEntity.ok(
                solicitacaoService.listarPendentes()
        );
    }
}