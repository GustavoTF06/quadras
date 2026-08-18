package com.quadras.quadras.service;

import com.quadras.quadras.dto.UsuarioCadastroDTO;
import com.quadras.quadras.dto.UsuarioResponseDTO;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.exception.CredenciaisInvalidasException;
import com.quadras.quadras.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.quadras.quadras.dto.LoginResponseDTO;
import com.quadras.quadras.dto.UsuarioLoginDTO;
import com.quadras.quadras.security.JwtService;

@Service
public class UsuarioService {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UsuarioResponseDTO criar(UsuarioCadastroDTO dados) {

        Usuario usuario = new Usuario();

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setCpf(dados.getCpf());
        usuario.setTelefone(dados.getTelefone());
        usuario.setDataNascimento(dados.getDataNascimento());

        String senhaHash = passwordEncoder.encode(dados.getSenha());
        usuario.setSenhaHash(senhaHash);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getUsuarioId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCpf(),
                usuarioSalvo.getTelefone(),
                usuarioSalvo.getDataNascimento(),
                usuarioSalvo.getEmailVerificado(),
                usuarioSalvo.getRole(),
                usuarioSalvo.getAtivo()
        );
    }

    public LoginResponseDTO login(UsuarioLoginDTO dados) {

        Usuario usuario = usuarioRepository
                .findByEmail(dados.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dados.getSenha(), usuario.getSenhaHash())) {
            throw new CredenciaisInvalidasException("Email ou senha inválidos");
        }

        String token = jwtService.gerarToken(usuario);

        return new LoginResponseDTO(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                token
        );
    }
}