package com.quadras.quadras.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private String senhaHash;

    private LocalDate dataNascimento;

    private Boolean emailVerificado = false;

    @Column(nullable = false)
    private String role = "CLIENTE";

    @Column(nullable = false)
    private Boolean ativo = true;

    private LocalDateTime criadoEm = LocalDateTime.now();

    private LocalDateTime atualizadoEm = LocalDateTime.now();

    private String tokenRedefinicaoHash;

    private LocalDateTime tokenRedefinicaoExpiraEm;

    // Getters e Setters

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getEmailVerificado() {
        return emailVerificado;
    }

    public void setEmailVerificado(Boolean emailVerificado) {
        this.emailVerificado = emailVerificado;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public String getTokenRedefinicaoHash() {
        return tokenRedefinicaoHash;
    }

    public void setTokenRedefinicaoHash(String tokenRedefinicaoHash) {
        this.tokenRedefinicaoHash = tokenRedefinicaoHash;
    }

    public LocalDateTime getTokenRedefinicaoExpiraEm() {
        return tokenRedefinicaoExpiraEm;
    }

    public void setTokenRedefinicaoExpiraEm(LocalDateTime tokenRedefinicaoExpiraEm) {
        this.tokenRedefinicaoExpiraEm = tokenRedefinicaoExpiraEm;
    }
}
