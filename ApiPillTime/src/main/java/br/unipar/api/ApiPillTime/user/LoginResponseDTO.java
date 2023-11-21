package br.unipar.api.ApiPillTime.user;

import br.unipar.api.ApiPillTime.model.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String token;
    private Long pessoaId;
    private TipoUsuario tipoUsuario;

    public LoginResponseDTO(String token, Long pessoaId) {
        this.token = token;
        this.pessoaId = pessoaId;
    }
}


