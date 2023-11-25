package br.unipar.api.ApiPillTime.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Future(message = "A data do alarme não pode estar no passado")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime alarme;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;

    @JsonIgnore
    private boolean statusAlarme = true;



}
