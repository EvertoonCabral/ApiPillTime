package br.unipar.api.ApiPillTime.model.dto;


import br.unipar.api.ApiPillTime.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaInsertDTO {


    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private String cpf;

    private String Telefone;

    private Endereco endereco;

    private boolean stAtivo;

}
