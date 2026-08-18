package com.quadras.quadras.dto;

import java.util.List;

public class ErroResponseDTO {

    private int status;
    private List<String> mensagens;

    public ErroResponseDTO(int status, List<String> mensagens) {
        this.status = status;
        this.mensagens = mensagens;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<String> mensagens) {
        this.mensagens = mensagens;
    }
}