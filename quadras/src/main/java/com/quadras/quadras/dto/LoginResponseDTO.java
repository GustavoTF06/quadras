package com.quadras.quadras.dto;

public class LoginResponseDTO {

    private Long usuarioId;
    private String nome;
    private String email;
    private String role;
    private String token;

    public LoginResponseDTO(
            Long usuarioId,
            String nome,
            String email,
            String role,
            String token) {

        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public LoginResponseDTO(Long usuarioId, String nome, String email, String role) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}