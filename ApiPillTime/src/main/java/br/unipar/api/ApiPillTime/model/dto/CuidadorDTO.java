package br.unipar.api.ApiPillTime.model.dto;

import br.unipar.api.ApiPillTime.model.Endereco;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuidadorDTO {

    @ApiModelProperty(notes = "Nome do Cuidador", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;

    private String email;
    private String Senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private String cpf;
    private String Telefone;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereçoPessoa")
    private EnderecoDTO endereco;
    private boolean stAtivo;
}
