package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cuidador")
@ApiModel(description = "Modelo para a representação de um cuidador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cuidador extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Remedio> listaRemedio;

    @OneToMany(mappedBy = "cuidador")
    private List<Idoso> listaIdoso;


}
