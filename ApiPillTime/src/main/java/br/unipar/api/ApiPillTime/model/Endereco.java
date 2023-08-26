package br.unipar.api.ApiPillTime.model;

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

@Entity
@Table(name = "Endereco")
@ApiModel(description = "Modelo de Endereço")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id gerada automaticamente pelo sistema")
    private Long id;
    @ApiModelProperty(notes = "Nome do Estado", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
private String estado;
    @ApiModelProperty(notes = "Nome da Cidade", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String cidade;
    private String bairro;
    private String rua;
    private int numeroResidencia;
    private String complemento;
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;

    private boolean stAtivo;


}
