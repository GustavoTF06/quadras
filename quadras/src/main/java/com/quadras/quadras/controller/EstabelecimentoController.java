package com.quadras.quadras.controller;

import com.quadras.quadras.dto.EstabelecimentoCadastroDTO;
import com.quadras.quadras.dto.EstabelecimentoResponseDTO;
import com.quadras.quadras.service.EstabelecimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoController(
            EstabelecimentoService estabelecimentoService) {

        this.estabelecimentoService = estabelecimentoService;
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoResponseDTO> criar(
            @Valid @RequestBody EstabelecimentoCadastroDTO dados,
            Authentication authentication) {

        Long usuarioId = ((com.quadras.quadras.entity.Usuario)
                authentication.getPrincipal()).getUsuarioId();

        EstabelecimentoResponseDTO resposta =
                estabelecimentoService.criar(dados, usuarioId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }
}