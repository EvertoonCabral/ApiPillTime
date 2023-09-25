package br.unipar.api.ApiPillTime.model.dto;


import br.unipar.api.ApiPillTime.model.Remedio;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmeDTO {

    private  String titulo;
    private String descricao;
    private IdosoDTO idoso;
    private List<RemedioDTO> RemediosIdosos;

    @Future(message = "A data do alarme não pode estar no passado")
    @ApiModelProperty(example = "25-09-2023 14:30")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime alarme;



}
