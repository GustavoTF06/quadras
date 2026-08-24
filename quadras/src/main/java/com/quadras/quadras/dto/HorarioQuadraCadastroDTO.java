package com.quadras.quadras.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class HorarioQuadraCadastroDTO {

    @NotNull
    private Long quadraId;

    @NotBlank
    private String diaSemana;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFim;

    public HorarioQuadraCadastroDTO() {
    }

    public Long getQuadraId() {
        return quadraId;
    }

    public void setQuadraId(Long quadraId) {
        this.quadraId = quadraId;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}