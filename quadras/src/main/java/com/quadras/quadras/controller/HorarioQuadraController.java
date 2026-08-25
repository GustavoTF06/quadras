package com.quadras.quadras.controller;

import com.quadras.quadras.dto.HorarioQuadraCadastroDTO;
import com.quadras.quadras.dto.HorarioQuadraResponseDTO;
import com.quadras.quadras.service.HorarioQuadraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioQuadraController {

    private final HorarioQuadraService horarioQuadraService;

    public HorarioQuadraController(
            HorarioQuadraService horarioQuadraService) {

        this.horarioQuadraService = horarioQuadraService;
    }

    @PostMapping
    public ResponseEntity<HorarioQuadraResponseDTO> criar(
            @Valid @RequestBody HorarioQuadraCadastroDTO dados,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        HorarioQuadraResponseDTO resposta =
                horarioQuadraService.criar(
                        dados,
                        usuarioId
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    @GetMapping("/quadra/{quadraId}")
    public ResponseEntity<List<HorarioQuadraResponseDTO>> listarPorQuadra(
            @PathVariable Long quadraId) {

        return ResponseEntity.ok(
                horarioQuadraService.listarPorQuadra(quadraId)
        );


    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioQuadraResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody HorarioQuadraCadastroDTO dados,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        HorarioQuadraResponseDTO resposta =
                horarioQuadraService.atualizar(
                        id,
                        dados,
                        usuarioId
                );

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        horarioQuadraService.excluir(id, usuarioId);

        return ResponseEntity.noContent().build();
    }
}