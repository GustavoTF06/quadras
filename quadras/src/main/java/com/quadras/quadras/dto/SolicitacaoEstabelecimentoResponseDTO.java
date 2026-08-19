package com.quadras.quadras.dto;

import java.time.LocalDateTime;

public class SolicitacaoEstabelecimentoResponseDTO {

    private Long id;
    private Long estabelecimentoId;
    private Long usuarioSolicitanteId;
    private Long administradorId;

    private String status;
    private String motivoRecusa;

    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAnalise;

    public SolicitacaoEstabelecimentoResponseDTO() {
    }

    public SolicitacaoEstabelecimentoResponseDTO(
            Long id,
            Long estabelecimentoId,
            Long usuarioSolicitanteId,
            Long administradorId,
            String status,
            String motivoRecusa,
            LocalDateTime dataSolicitacao,
            LocalDateTime dataAnalise) {

        this.id = id;
        this.estabelecimentoId = estabelecimentoId;
        this.usuarioSolicitanteId = usuarioSolicitanteId;
        this.administradorId = administradorId;
        this.status = status;
        this.motivoRecusa = motivoRecusa;
        this.dataSolicitacao = dataSolicitacao;
        this.dataAnalise = dataAnalise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstabelecimentoId() {
        return estabelecimentoId;
    }

    public void setEstabelecimentoId(Long estabelecimentoId) {
        this.estabelecimentoId = estabelecimentoId;
    }

    public Long getUsuarioSolicitanteId() {
        return usuarioSolicitanteId;
    }

    public void setUsuarioSolicitanteId(Long usuarioSolicitanteId) {
        this.usuarioSolicitanteId = usuarioSolicitanteId;
    }

    public Long getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Long administradorId) {
        this.administradorId = administradorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivoRecusa() {
        return motivoRecusa;
    }

    public void setMotivoRecusa(String motivoRecusa) {
        this.motivoRecusa = motivoRecusa;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(LocalDateTime dataAnalise) {
        this.dataAnalise = dataAnalise;
    }
}