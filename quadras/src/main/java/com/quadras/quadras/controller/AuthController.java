package com.quadras.quadras.controller;

import com.quadras.quadras.dto.LoginResponseDTO;
import com.quadras.quadras.dto.UsuarioLoginDTO;
import com.quadras.quadras.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody UsuarioLoginDTO dados) {

        LoginResponseDTO resposta = usuarioService.login(dados);

        return ResponseEntity.ok(resposta);
    }


}