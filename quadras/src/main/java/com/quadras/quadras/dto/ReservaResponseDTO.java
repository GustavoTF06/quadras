package com.quadras.quadras.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservaResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long quadraId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private BigDecimal valor;
    private Integer numeroPessoas;
    private String status;
    private LocalDateTime dataReserva;
    private LocalDateTime dataPagamento;
    private String observacao;

    public ReservaResponseDTO() {
    }

    public ReservaResponseDTO(
            Long id,
            Long usuarioId,
            Long quadraId,
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            BigDecimal valor,
            Integer numeroPessoas,
            String status,
            LocalDateTime dataReserva,
            LocalDateTime dataPagamento,
            String observacao) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.quadraId = quadraId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.numeroPessoas = numeroPessoas;
        this.status = status;
        this.dataReserva = dataReserva;
        this.dataPagamento = dataPagamento;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getQuadraId() {
        return quadraId;
    }

    public void setQuadraId(Long quadraId) {
        this.quadraId = quadraId;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(Integer numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}