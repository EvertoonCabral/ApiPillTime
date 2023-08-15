package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@ApiModel(description = "Modelo de Pessoa")
@Table(name = "Pessoa")
public class Pessoa {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@ApiModelProperty(notes = "Id gerada automaticamente pelo sistema")
    private Long id;
    @ApiModelProperty(notes = "Nome da Pessoa", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;
    @ManyToOne
    private Endereco endereco;
    @UpdateTimestamp
    private Date dataNascimento;

    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;
    private String cpf;
    @OneToOne
    private Telefone Telefone;
    private boolean stAtivo;


    public Pessoa(Long id, String nome, Endereco endereco, Date dataNascimento, Date dataCadastro, String cpf, br.unipar.api.ApiPillTime.model.Telefone telefone, boolean stAtivo) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.cpf = cpf;
        Telefone = telefone;
        this.stAtivo = stAtivo = true;
    }

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public br.unipar.api.ApiPillTime.model.Telefone getTelefone() {
        return Telefone;
    }

    public void setTelefone(br.unipar.api.ApiPillTime.model.Telefone telefone) {
        Telefone = telefone;
    }

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }
}
