package com.clinicaesteticaapi.dto;

public class PacoteResponseDTO {

    private Long id;
    private Long procedimentosId;
    private String nome;
    private String descricao;
    private java.math.BigDecimal preco;
    private Integer sessoes;
    private java.time.LocalDateTime validade;
    private Boolean ativo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProcedimentosId() { return procedimentosId; }
    public void setProcedimentosId(Long procedimentosId) { this.procedimentosId = procedimentosId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public java.math.BigDecimal getPreco() { return preco; }
    public void setPreco(java.math.BigDecimal preco) { this.preco = preco; }
    public Integer getSessoes() { return sessoes; }
    public void setSessoes(Integer sessoes) { this.sessoes = sessoes; }
    public java.time.LocalDateTime getValidade() { return validade; }
    public void setValidade(java.time.LocalDateTime validade) { this.validade = validade; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
