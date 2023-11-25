package br.unipar.api.ApiPillTime.model.dto;

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
public class AlarmeDTOInsert {

    /*
    private  String titulo;
    private String descricao;
    @Future(message = "A data do alarme não pode estar no passado")
    private LocalDateTime alarme;
*/

    private  String titulo;
    private String descricao;
    private List<RemedioDTO> RemediosIdosos;

    @Future(message = "A data do alarme não pode estar no passado")
    @ApiModelProperty(example = "25-09-2023 14:30")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime alarme;


}
