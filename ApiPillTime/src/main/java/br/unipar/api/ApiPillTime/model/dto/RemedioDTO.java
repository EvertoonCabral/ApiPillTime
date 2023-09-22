package br.unipar.api.ApiPillTime.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemedioDTO {

    private String nome;
    private String marcaRemedio;
    private String dosagem;
    private String formaFarmaceutico;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataValidade;
    private String observacoes;

}
