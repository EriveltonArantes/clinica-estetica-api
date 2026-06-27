package com.clinicaesteticaapi.dto;

import jakarta.validation.constraints.*;

public class AgendamentoRequestDTO {

    @NotNull(message = "ProfissionalId é obrigatório")
    @Positive(message = "ProfissionalId deve ser um ID válido (positivo)")
    private Long profissionalId;
    @NotNull(message = "ProcedimentoId é obrigatório")
    @Positive(message = "ProcedimentoId deve ser um ID válido (positivo)")
    private Long procedimentoId;
    @NotBlank(message = "nome cliente não pode estar em branco")
    private String nomeCliente;
    @NotBlank(message = "email cliente não pode estar em branco")
    @Email(message = "email cliente precisa ser um e-mail válido")
    private String emailCliente;
    @NotBlank(message = "telefone cliente não pode estar em branco")
    private String telefoneCliente;
    @FutureOrPresent(message = "data hora não pode ser retroativo")
    @NotNull(message = "data hora não pode ser nulo")
    private java.time.LocalDateTime dataHora;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private java.math.BigDecimal valor;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }
    public Long getProcedimentoId() { return procedimentoId; }
    public void setProcedimentoId(Long procedimentoId) { this.procedimentoId = procedimentoId; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public String getEmailCliente() { return emailCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }
    public String getTelefoneCliente() { return telefoneCliente; }
    public void setTelefoneCliente(String telefoneCliente) { this.telefoneCliente = telefoneCliente; }
    public java.time.LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(java.time.LocalDateTime dataHora) { this.dataHora = dataHora; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
