package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Idosos idoso;
    @OneToMany
    private List<Remedio> RemediosIdosos;
    private LocalDateTime alarme;
    private boolean statusAlarme;



}
