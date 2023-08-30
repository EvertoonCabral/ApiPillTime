package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Telefone")
@ApiModel(description = "Modelo de Telefone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID gerado automaticamente pelo sistema")
    private Long id;
    private String dd;
    private String telefone;
    private boolean stAtivo;


}
