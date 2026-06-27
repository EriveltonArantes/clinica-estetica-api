package com.clinicaesteticaapi.dto;

import jakarta.validation.constraints.*;

public class PacoteRequestDTO {

    @NotNull(message = "ProcedimentosId é obrigatório")
    @Positive(message = "ProcedimentosId deve ser um ID válido (positivo)")
    private Long procedimentosId;
    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @DecimalMin(value = "0.0", message = "preco não pode ser negativo")
    @NotNull(message = "preco não pode ser nulo")
    private java.math.BigDecimal preco;
    @NotNull(message = "sessoes não pode ser nulo")
    private Integer sessoes;
    @NotNull(message = "validade não pode ser nulo")
    private java.time.LocalDateTime validade;
    @NotNull(message = "ativo não pode ser nulo")
    private Boolean ativo;

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
