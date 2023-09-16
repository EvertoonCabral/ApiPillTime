package br.unipar.api.ApiPillTime.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Idoso")
@ApiModel(description = "Modelo de representação de um idoso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Idoso{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Nome do Idoso", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCadastro;

    private String cpf;

    private String Telefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereçoPessoa")
    private Endereco endereco;

    @OneToMany
    private List<Alarme> alarmesIdoso= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CuidadorId")
    private Cuidador cuidador;

    private String observacao;

    private boolean stAtivo;

    @OneToMany(mappedBy = "idoso")
    private List<RemedioIdoso> remediosAssociados;


}
