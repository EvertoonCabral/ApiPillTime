package br.unipar.api.ApiPillTime.model;

import br.unipar.api.ApiPillTime.user.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ApiModel(description = "Modelo de Pessoa")
@Table(name = "Pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoUsuario")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cuidador.class, name = "C"),
})

public abstract class Pessoa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id gerada automaticamente pelo sistema")
    @JsonIgnore
    private Long id;

    @ApiModelProperty(notes = "Nome da Pessoa", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;

    @Email
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @Size(min = 11, max = 11)
    private String cpf;

    private String Telefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereçoPessoa")
    private Endereco endereco;
    @JsonIgnore
    private boolean stAtivo;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}
