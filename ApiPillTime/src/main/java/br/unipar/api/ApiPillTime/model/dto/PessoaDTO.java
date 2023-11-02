package br.unipar.api.ApiPillTime.model.dto;


import br.unipar.api.ApiPillTime.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {


    private String nome;
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private java.util.Date dataNascimento;

    private String cpf;

    private String Telefone;

    private Endereco endereco;

    private String observacao;


}
