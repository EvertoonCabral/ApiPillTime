package br.unipar.api.ApiPillTime.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {


    private String estado;

    private String cidade;

    private String bairro;

    private String rua;

    private int numeroResidencia;

    private String complemento;

}

