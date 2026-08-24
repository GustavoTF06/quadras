package com.quadras.quadras.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "horario_quadra")
public class HorarioQuadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quadra_id", nullable = false)
    private Quadra quadra;

    @Column(name = "dia_semana", nullable = false, length = 20)
    private String diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    public HorarioQuadra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
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