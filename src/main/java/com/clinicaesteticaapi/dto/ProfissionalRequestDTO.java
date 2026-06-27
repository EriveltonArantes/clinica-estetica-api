package com.clinicaesteticaapi.dto;

import jakarta.validation.constraints.*;

public class ProfissionalRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "cpf não pode estar em branco")
    @Size(min = 11, max = 14, message = "cpf deve ter entre 11 e 14 dígitos")
    private String cpf;
    @NotBlank(message = "email não pode estar em branco")
    @Email(message = "email precisa ser um e-mail válido")
    private String email;
    @NotBlank(message = "especialidade não pode estar em branco")
    private String especialidade;
    @NotBlank(message = "coren não pode estar em branco")
    @Size(min = 4, max = 20, message = "coren inválido")
    private String coren;
    @NotNull(message = "disponivel não pode ser nulo")
    private Boolean disponivel;
    @NotBlank(message = "telefone não pode estar em branco")
    private String telefone;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getCoren() { return coren; }
    public void setCoren(String coren) { this.coren = coren; }
    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
