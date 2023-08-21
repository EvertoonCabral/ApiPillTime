package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
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



}
