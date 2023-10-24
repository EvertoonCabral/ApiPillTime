package br.unipar.api.ApiPillTime.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class IdosoDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 255, message = "O nome deve conter entre 1 e 255 caracteres")
    private String nome;

    @Email(message = "O e-mail é inválido")
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private String cpf;

    private String telefone;

    private EnderecoDTO endereco;

    private String observacao;
    }






