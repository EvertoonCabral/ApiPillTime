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
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Idoso")
@ApiModel(description = "Modelo de representação de um idoso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Idoso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    private Telefone Telefone;

    @ManyToOne
    private Endereco endereco;

    private boolean stAtivo;

    @OneToMany
    private List<Alarme> alarmesIdoso;

    @ManyToOne
    @JoinColumn(name = "CuidadorId")
    private Cuidador cuidador;

    private String observacao;
    
    @OneToMany(mappedBy = "idoso")
    private List<RemedioIdoso> remediosAssociados;




}
