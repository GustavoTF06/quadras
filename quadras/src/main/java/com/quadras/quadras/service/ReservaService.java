package com.quadras.quadras.service;

import com.quadras.quadras.dto.ReservaCadastroDTO;
import com.quadras.quadras.dto.ReservaResponseDTO;
import com.quadras.quadras.entity.Quadra;
import com.quadras.quadras.entity.Reserva;
import com.quadras.quadras.entity.Usuario;
import com.quadras.quadras.repository.QuadraRepository;
import com.quadras.quadras.repository.ReservaRepository;
import com.quadras.quadras.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final QuadraRepository quadraRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            QuadraRepository quadraRepository,
            UsuarioRepository usuarioRepository) {

        this.reservaRepository = reservaRepository;
        this.quadraRepository = quadraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ReservaResponseDTO criar(
            ReservaCadastroDTO dados,
            Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Quadra quadra = quadraRepository.findById(dados.getQuadraId())
                .orElseThrow(() ->
                        new RuntimeException("Quadra não encontrada"));


        if (!"ATIVA".equals(quadra.getStatus())) {
            throw new RuntimeException(
                    "Esta quadra não está disponível para reservas");
        }


        if (!dados.getDataInicio().isBefore(dados.getDataFim())) {
            throw new RuntimeException(
                    "A data de início deve ser anterior à data de fim");
        }


        if (dados.getDataInicio().isBefore(LocalDateTime.now())) {
            throw new RuntimeException(
                    "Não é possível reservar um horário no passado");
        }


        List<Reserva> conflitos =
                reservaRepository
                        .findByQuadraIdAndStatusInAndDataInicioLessThanAndDataFimGreaterThan(
                                dados.getQuadraId(),
                                List.of("PENDENTE", "PAGA"),
                                dados.getDataFim(),
                                dados.getDataInicio()
                        );

        if (!conflitos.isEmpty()) {
            throw new RuntimeException(
                    "Já existe uma reserva para este horário");
        }

        Reserva reserva = new Reserva();

        reserva.setUsuario(usuario);
        reserva.setQuadra(quadra);
        reserva.setDataInicio(dados.getDataInicio());
        reserva.setDataFim(dados.getDataFim());


        long minutos = java.time.Duration.between(
                dados.getDataInicio(),
                dados.getDataFim()
        ).toMinutes();

        if (minutos % 60 != 0) {
            throw new RuntimeException(
                    "A reserva deve ter duração em horas completas");
        }

        long horas = minutos / 60;

        BigDecimal valorTotal =
                quadra.getValorHora()
                        .multiply(BigDecimal.valueOf(horas));

        reserva.setValor(valorTotal);

        reserva.setNumeroPessoas(dados.getNumeroPessoas());
        reserva.setObservacao(dados.getObservacao());

        reserva.setStatus("PENDENTE");
        reserva.setDataReserva(LocalDateTime.now());

        Reserva salva = reservaRepository.save(reserva);

        return converterParaResponse(salva);
    }

    private ReservaResponseDTO converterParaResponse(
            Reserva reserva) {

        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getUsuario().getUsuarioId(),
                reserva.getQuadra().getId(),
                reserva.getDataInicio(),
                reserva.getDataFim(),
                reserva.getValor(),
                reserva.getNumeroPessoas(),
                reserva.getStatus(),
                reserva.getDataReserva(),
                reserva.getDataPagamento(),
                reserva.getObservacao()
        );
    }

    public List<ReservaResponseDTO> listarPorUsuario(Long usuarioId) {

        return reservaRepository
                .findByUsuarioUsuarioId(usuarioId)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public List<ReservaResponseDTO> listarPorQuadra(
            Long quadraId,
            Long usuarioId) {

        Quadra quadra = quadraRepository.findById(quadraId)
                .orElseThrow(() ->
                        new RuntimeException("Quadra não encontrada"));

        Long proprietarioId = quadra
                .getEstabelecimento()
                .getUsuario()
                .getUsuarioId();

        if (!proprietarioId.equals(usuarioId)) {
            throw new RuntimeException(
                    "Você não é o proprietário desta quadra");
        }

        return reservaRepository
                .findByQuadraId(quadraId)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public ReservaResponseDTO cancelar(
            Long reservaId,
            Long usuarioId) {

        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() ->
                        new RuntimeException("Reserva não encontrada"));

        if (!reserva.getUsuario()
                .getUsuarioId()
                .equals(usuarioId)) {

            throw new RuntimeException(
                    "Você não pode cancelar esta reserva");
        }

        if ("CANCELADA".equals(reserva.getStatus())) {
            throw new RuntimeException(
                    "Esta reserva já está cancelada");
        }

        LocalDateTime limiteCancelamento =
                reserva.getDataInicio().minusHours(24);

        if (LocalDateTime.now().isAfter(limiteCancelamento)) {
            throw new RuntimeException(
                    "Esta reserva não pode mais ser cancelada. " +
                            "O prazo de 24 horas foi ultrapassado.");
        }

        reserva.setStatus("CANCELADA");

        Reserva cancelada =
                reservaRepository.save(reserva);

        return converterParaResponse(cancelada);
    }
}