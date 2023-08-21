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
@Table(name = "Remedio")
@ApiModel(description = "Modelo para representação de Remedio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Remedio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id autogerado pelo sistema")
    private Long id;
    @ApiModelProperty(notes = "Nome do Remedio", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;
    @ManyToOne
    private MarcaRemedio marcaRemedio;
    private String dosagem;
    private String formaFarmaceutico;
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;
    @UpdateTimestamp
    private Date dataValidade;
    private String observacoes;

    private boolean stAtivo;



}
