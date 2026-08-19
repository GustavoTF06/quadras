package com.quadras.quadras.service;

import com.quadras.quadras.dto.EstabelecimentoCadastroDTO;
import com.quadras.quadras.dto.EstabelecimentoResponseDTO;
import com.quadras.quadras.entity.Estabelecimento;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.repository.EstabelecimentoRepository;
import com.quadras.quadras.repository.SolicitacaoEstabelecimentoRepository;
import com.quadras.quadras.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.quadras.quadras.entity.SolicitacaoEstabelecimento;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SolicitacaoEstabelecimentoRepository solicitacaoRepository;

    public EstabelecimentoService(
            EstabelecimentoRepository estabelecimentoRepository,
            UsuarioRepository usuarioRepository,
            SolicitacaoEstabelecimentoRepository solicitacaoRepository) {

        this.estabelecimentoRepository = estabelecimentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public EstabelecimentoResponseDTO criar(
            EstabelecimentoCadastroDTO dados,
            Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if (!"PROPRIETARIO".equals(usuario.getRole())) {
            throw new RuntimeException(
                    "Apenas proprietários podem cadastrar estabelecimentos");
        }

        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setUsuario(usuario);
        estabelecimento.setNome(dados.getNome());
        estabelecimento.setDescricao(dados.getDescricao());
        estabelecimento.setTelefone(dados.getTelefone());
        estabelecimento.setCep(dados.getCep());
        estabelecimento.setLogradouro(dados.getLogradouro());
        estabelecimento.setNumero(dados.getNumero());
        estabelecimento.setComplemento(dados.getComplemento());
        estabelecimento.setBairro(dados.getBairro());
        estabelecimento.setCidade(dados.getCidade());
        estabelecimento.setEstado(dados.getEstado());
        estabelecimento.setLatitude(dados.getLatitude());
        estabelecimento.setLongitude(dados.getLongitude());

        estabelecimento.setStatus("PENDENTE");
        estabelecimento.setDataCadastro(
                java.time.LocalDateTime.now()
        );

        Estabelecimento salvo =
                estabelecimentoRepository.save(estabelecimento);

        SolicitacaoEstabelecimento solicitacao =
                new SolicitacaoEstabelecimento();

        solicitacao.setEstabelecimento(salvo);
        solicitacao.setUsuarioSolicitante(usuario);
        solicitacao.setStatus("PENDENTE");
        solicitacao.setDataSolicitacao(
                java.time.LocalDateTime.now()
        );

        solicitacaoRepository.save(solicitacao);

        return converterParaResponse(salvo);
    }

    private EstabelecimentoResponseDTO converterParaResponse(
            Estabelecimento estabelecimento) {

        return new EstabelecimentoResponseDTO(
                estabelecimento.getId(),
                estabelecimento.getUsuario().getUsuarioId(),
                estabelecimento.getNome(),
                estabelecimento.getDescricao(),
                estabelecimento.getTelefone(),
                estabelecimento.getCep(),
                estabelecimento.getLogradouro(),
                estabelecimento.getNumero(),
                estabelecimento.getComplemento(),
                estabelecimento.getBairro(),
                estabelecimento.getCidade(),
                estabelecimento.getEstado(),
                estabelecimento.getLatitude(),
                estabelecimento.getLongitude(),
                estabelecimento.getStatus()
        );
    }
}