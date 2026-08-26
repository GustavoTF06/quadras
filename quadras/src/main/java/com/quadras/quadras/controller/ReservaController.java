package com.quadras.quadras.controller;

import com.quadras.quadras.dto.ReservaCadastroDTO;
import com.quadras.quadras.dto.ReservaResponseDTO;
import com.quadras.quadras.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(
            @Valid @RequestBody ReservaCadastroDTO dados,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        ReservaResponseDTO resposta =
                reservaService.criar(
                        dados,
                        usuarioId
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<ReservaResponseDTO>> listarMinhasReservas(
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        return ResponseEntity.ok(
                reservaService.listarPorUsuario(usuarioId)
        );
    }

    @GetMapping("/quadra/{quadraId}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorQuadra(
            @PathVariable Long quadraId,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        return ResponseEntity.ok(
                reservaService.listarPorQuadra(
                        quadraId,
                        usuarioId
                )
        );
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelar(
            @PathVariable Long id,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        ReservaResponseDTO resposta =
                reservaService.cancelar(
                        id,
                        usuarioId
                );

        return ResponseEntity.ok(resposta);
    }
}