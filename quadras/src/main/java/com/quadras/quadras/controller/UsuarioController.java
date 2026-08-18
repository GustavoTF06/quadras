package com.quadras.quadras.controller;

import com.quadras.quadras.dto.UsuarioCadastroDTO;
import com.quadras.quadras.dto.UsuarioResponseDTO;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(
            @Valid @RequestBody UsuarioCadastroDTO dados) {

        UsuarioResponseDTO usuarioCriado = usuarioService.criar(dados);

        return ResponseEntity.ok(usuarioCriado);
    }
}