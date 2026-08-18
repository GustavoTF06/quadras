package com.quadras.quadras.service;

import com.quadras.quadras.dto.UsuarioCadastroDTO;
import com.quadras.quadras.dto.UsuarioResponseDTO;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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
}