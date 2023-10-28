package br.unipar.api.ApiPillTime.model;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(description = "Modelo para a representação de um cuidador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@DiscriminatorValue("CUIDADOR")
public class Cuidador  extends Pessoa{


    @OneToMany
    private List<Remedio> listaRemedio = new ArrayList<>();

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Idoso> listaIdoso= new ArrayList<>();



}
