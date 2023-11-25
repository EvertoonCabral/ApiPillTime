package br.unipar.api.ApiPillTime.model;

import br.unipar.api.ApiPillTime.model.dto.RemedioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Alarme")
@ApiModel(description = "Modelo de Alarme")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alarme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificador gerado automaticamente pelo Sistema")
    private Long id;
    private String titulo;
    private String Descricao;
    private Date dtCadastrado;
    @ManyToOne
    @JoinColumn(name = "IdosoId")
    @JsonIgnore
    private Idoso idoso;
    @OneToMany
    private List<Remedio> RemediosIdosos;
    private LocalDateTime alarme;
    private boolean statusAlarme;



}
