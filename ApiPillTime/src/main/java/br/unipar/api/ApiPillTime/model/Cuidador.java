package br.unipar.api.ApiPillTime.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ApiModel(description = "Modelo para a representação de um cuidador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@DiscriminatorValue("C")
public class Cuidador  extends Pessoa{


    @OneToMany
    private List<Remedio> listaRemedio = new ArrayList<>();

    @OneToMany(mappedBy = "cuidador")
    private List<Idoso> listaIdoso= new ArrayList<>();



}
