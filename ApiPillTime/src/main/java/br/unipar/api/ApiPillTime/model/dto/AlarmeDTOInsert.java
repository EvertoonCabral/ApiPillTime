package br.unipar.api.ApiPillTime.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmeDTOInsert {

    private  String titulo;
    private String descricao;
    @Future(message = "A data do alarme não pode estar no passado")
    private LocalDateTime alarme;


}
