package br.unipar.api.ApiPillTime.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @EqualsAndHashCode(of = "id")
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


    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;

    private String cpf;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "telefonePessoa")
        private Telefone Telefone;


        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "endereçoPessoa")
        private Endereco endereco;

    private boolean stAtivo;



}
