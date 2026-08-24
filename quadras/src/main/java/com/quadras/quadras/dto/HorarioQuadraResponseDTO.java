package com.quadras.quadras.dto;

import java.time.LocalTime;

public class HorarioQuadraResponseDTO {

    private Long id;
    private Long quadraId;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public HorarioQuadraResponseDTO() {
    }

    public HorarioQuadraResponseDTO(
            Long id,
            Long quadraId,
            String diaSemana,
            LocalTime horaInicio,
            LocalTime horaFim) {

        this.id = id;
        this.quadraId = quadraId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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