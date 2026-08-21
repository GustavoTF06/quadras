package com.quadras.quadras.controller;

import com.quadras.quadras.dto.QuadraCadastroDTO;
import com.quadras.quadras.dto.QuadraResponseDTO;
import com.quadras.quadras.service.QuadraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quadras")
public class QuadraController {

    private final QuadraService quadraService;

    public QuadraController(QuadraService quadraService) {
        this.quadraService = quadraService;
    }

    @PostMapping
    public ResponseEntity<QuadraResponseDTO> criar(
            @Valid @RequestBody QuadraCadastroDTO dados,
            Authentication authentication) {

        Long usuarioId =
                ((com.quadras.quadras.entity.Usuario)
                        authentication.getPrincipal()).getUsuarioId();

        QuadraResponseDTO resposta =
                quadraService.criar(dados, usuarioId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }
}