package com.quadras.quadras.dto;

public class QuadraResponseDTO {

    private Long id;
    private Long estabelecimentoId;
    private Long categoriaId;
    private String categoriaNome;
    private String nome;
    private String descricao;
    private Integer capacidade;
    private String status;

    public QuadraResponseDTO() {
    }

    public QuadraResponseDTO(
            Long id,
            Long estabelecimentoId,
            Long categoriaId,
            String categoriaNome,
            String nome,
            String descricao,
            Integer capacidade,
            String status) {

        this.id = id;
        this.estabelecimentoId = estabelecimentoId;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
        this.nome = nome;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.status = status;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}