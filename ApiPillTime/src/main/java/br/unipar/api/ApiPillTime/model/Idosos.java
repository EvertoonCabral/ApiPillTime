package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Idoso")
@ApiModel(description = "Modelo de representação de um idoso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Idosos extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Alarme> alarmesIdoso;


    @ManyToOne
    @JoinColumn(name = "CuidadorId")
    private Cuidador cuidador;

    private String observacao;

    private boolean stAtivo;


}
