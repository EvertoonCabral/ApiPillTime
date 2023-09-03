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
import java.util.List;

@Entity
@Table(name = "Cuidador")
@ApiModel(description = "Modelo para a representação de um cuidador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Cuidador{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne
    private Telefone Telefone;

    @ManyToOne
    private Endereco endereco;

    private boolean stAtivo;

    @OneToMany
    private List<Remedio> listaRemedio;

    @OneToMany
    private List<Idosos> listaIdoso;

}
