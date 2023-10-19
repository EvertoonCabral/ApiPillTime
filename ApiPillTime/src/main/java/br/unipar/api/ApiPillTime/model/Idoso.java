package br.unipar.api.ApiPillTime.model;

import br.unipar.api.ApiPillTime.model.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Idoso")
@ApiModel(description = "Modelo de representação de um idoso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Idoso extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Alarme> alarmesIdoso= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CuidadorId")
    private Cuidador cuidador;


}


