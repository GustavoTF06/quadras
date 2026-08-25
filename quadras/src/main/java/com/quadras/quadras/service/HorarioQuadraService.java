package com.quadras.quadras.service;

import com.quadras.quadras.dto.HorarioQuadraCadastroDTO;
import com.quadras.quadras.dto.HorarioQuadraResponseDTO;
import com.quadras.quadras.entity.HorarioQuadra;
import com.quadras.quadras.entity.Quadra;
import com.quadras.quadras.repository.HorarioQuadraRepository;
import com.quadras.quadras.repository.QuadraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioQuadraService {

    private final HorarioQuadraRepository horarioQuadraRepository;
    private final QuadraRepository quadraRepository;

    public HorarioQuadraService(
            HorarioQuadraRepository horarioQuadraRepository,
            QuadraRepository quadraRepository) {

        this.horarioQuadraRepository = horarioQuadraRepository;
        this.quadraRepository = quadraRepository;
    }

    public HorarioQuadraResponseDTO criar(
            HorarioQuadraCadastroDTO dados,
            Long usuarioId) {

        Quadra quadra = quadraRepository.findById(dados.getQuadraId())
                .orElseThrow(() ->
                        new RuntimeException("Quadra não encontrada"));

        if (!quadra.getEstabelecimento()
                .getUsuario()
                .getUsuarioId()
                .equals(usuarioId)) {

            throw new RuntimeException(
                    "Você não é o proprietário desta quadra");
        }

        if (!dados.getHoraInicio().isBefore(dados.getHoraFim())) {
            throw new RuntimeException(
                    "A hora de início deve ser anterior à hora de fim");
        }

        String diaSemana = dados.getDiaSemana().toUpperCase();

        if (!List.of(
                "SEGUNDA",
                "TERCA",
                "QUARTA",
                "QUINTA",
                "SEXTA",
                "SABADO",
                "DOMINGO"
        ).contains(diaSemana)) {

            throw new RuntimeException(
                    "Dia da semana inválido");
        }

        List<HorarioQuadra> horarios =
                horarioQuadraRepository
                        .findByQuadraIdAndDiaSemana(
                                dados.getQuadraId(),
                                diaSemana
                        );

        for (HorarioQuadra horario : horarios) {

            boolean conflito =
                    dados.getHoraInicio().isBefore(horario.getHoraFim())
                            && dados.getHoraFim().isAfter(horario.getHoraInicio());

            if (conflito) {
                throw new RuntimeException(
                        "Já existe um horário que conflita com este período");
            }
        }

        HorarioQuadra horario = new HorarioQuadra();

        horario.setQuadra(quadra);
        horario.setDiaSemana(diaSemana);
        horario.setHoraInicio(dados.getHoraInicio());
        horario.setHoraFim(dados.getHoraFim());

        HorarioQuadra salvo =
                horarioQuadraRepository.save(horario);

        return converterParaResponse(salvo);
    }

    private HorarioQuadraResponseDTO converterParaResponse(
            HorarioQuadra horario) {

        return new HorarioQuadraResponseDTO(
                horario.getId(),
                horario.getQuadra().getId(),
                horario.getDiaSemana(),
                horario.getHoraInicio(),
                horario.getHoraFim()
        );
    }

    public List<HorarioQuadraResponseDTO> listarPorQuadra(Long quadraId) {

        return horarioQuadraRepository
                .findByQuadraId(quadraId)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public HorarioQuadraResponseDTO atualizar(
            Long id,
            HorarioQuadraCadastroDTO dados,
            Long usuarioId) {

        HorarioQuadra horario = horarioQuadraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Horário não encontrado"));

        if (!horario.getQuadra()
                .getEstabelecimento()
                .getUsuario()
                .getUsuarioId()
                .equals(usuarioId)) {

            throw new RuntimeException(
                    "Você não é o proprietário desta quadra");
        }

        if (!dados.getHoraInicio().isBefore(dados.getHoraFim())) {
            throw new RuntimeException(
                    "A hora de início deve ser anterior à hora de fim");
        }

        String diaSemana = dados.getDiaSemana().toUpperCase();

        if (!List.of(
                "SEGUNDA",
                "TERCA",
                "QUARTA",
                "QUINTA",
                "SEXTA",
                "SABADO",
                "DOMINGO"
        ).contains(diaSemana)) {

            throw new RuntimeException(
                    "Dia da semana inválido");
        }

        List<HorarioQuadra> horarios =
                horarioQuadraRepository
                        .findByQuadraIdAndDiaSemana(
                                horario.getQuadra().getId(),
                                diaSemana
                        );

        for (HorarioQuadra outro : horarios) {

            if (outro.getId().equals(id)) {
                continue;
            }

            boolean conflito =
                    dados.getHoraInicio().isBefore(outro.getHoraFim())
                            && dados.getHoraFim().isAfter(outro.getHoraInicio());

            if (conflito) {
                throw new RuntimeException(
                        "Já existe um horário que conflita com este período");
            }
        }

        horario.setDiaSemana(diaSemana);
        horario.setHoraInicio(dados.getHoraInicio());
        horario.setHoraFim(dados.getHoraFim());

        HorarioQuadra atualizado =
                horarioQuadraRepository.save(horario);

        return converterParaResponse(atualizado);
    }

    public void excluir(Long id, Long usuarioId) {

        HorarioQuadra horario = horarioQuadraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Horário não encontrado"));

        if (!horario.getQuadra()
                .getEstabelecimento()
                .getUsuario()
                .getUsuarioId()
                .equals(usuarioId)) {

            throw new RuntimeException(
                    "Você não é o proprietário desta quadra");
        }

        horarioQuadraRepository.delete(horario);
    }
}