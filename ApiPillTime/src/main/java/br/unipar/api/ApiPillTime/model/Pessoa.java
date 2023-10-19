package br.unipar.api.ApiPillTime.model;

import br.unipar.api.ApiPillTime.user.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class Pessoa {


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

    @Email
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private java.util.Date dataNascimento;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCadastro;

    private String cpf;

    private String Telefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereçoPessoa")
    private Endereco endereco;

    private boolean stAtivo;

    private String observacao;

    private TipoUsuario tipoUsuario;

    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario usuario;


}
