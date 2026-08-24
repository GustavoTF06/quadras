package com.quadras.quadras.controller;

import com.quadras.quadras.dto.HorarioQuadraCadastroDTO;
import com.quadras.quadras.dto.HorarioQuadraResponseDTO;
import com.quadras.quadras.service.HorarioQuadraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}