package com.quadras.quadras.service;

import com.quadras.quadras.dto.QuadraCadastroDTO;
import com.quadras.quadras.dto.QuadraResponseDTO;
import com.quadras.quadras.entity.Categoria;
import com.quadras.quadras.entity.Estabelecimento;
import com.quadras.quadras.entity.Quadra;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.repository.CategoriaRepository;
import com.quadras.quadras.repository.EstabelecimentoRepository;
import com.quadras.quadras.repository.QuadraRepository;
import com.quadras.quadras.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class QuadraService {

    private final QuadraRepository quadraRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public QuadraService(
            QuadraRepository quadraRepository,
            EstabelecimentoRepository estabelecimentoRepository,
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository) {

        this.quadraRepository = quadraRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public QuadraResponseDTO criar(
            QuadraCadastroDTO dados,
            Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if (!"PROPRIETARIO".equals(usuario.getRole())) {
            throw new RuntimeException(
                    "Apenas proprietários podem cadastrar quadras");
        }

        Estabelecimento estabelecimento =
                estabelecimentoRepository.findById(
                        dados.getEstabelecimentoId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Estabelecimento não encontrado"));

        if (!"APROVADO".equals(estabelecimento.getStatus())) {
            throw new RuntimeException(
                    "O estabelecimento ainda não foi aprovado");
        }

        if (!estabelecimento.getUsuario().getUsuarioId()
                .equals(usuarioId)) {

            throw new RuntimeException(
                    "Você não é o proprietário deste estabelecimento");
        }

        Categoria categoria =
                categoriaRepository.findById(
                        dados.getCategoriaId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Categoria não encontrada"));

        Quadra quadra = new Quadra();

        quadra.setEstabelecimento(estabelecimento);
        quadra.setCategoria(categoria);
        quadra.setNome(dados.getNome());
        quadra.setDescricao(dados.getDescricao());
        quadra.setCapacidade(dados.getCapacidade());
        quadra.setStatus("ATIVA");

        Quadra salva = quadraRepository.save(quadra);

        return converterParaResponse(salva);
    }

    private QuadraResponseDTO converterParaResponse(Quadra quadra) {

        return new QuadraResponseDTO(
                quadra.getId(),
                quadra.getEstabelecimento().getId(),
                quadra.getCategoria().getId(),
                quadra.getCategoria().getNome(),
                quadra.getNome(),
                quadra.getDescricao(),
                quadra.getCapacidade(),
                quadra.getStatus()
        );
    }
}