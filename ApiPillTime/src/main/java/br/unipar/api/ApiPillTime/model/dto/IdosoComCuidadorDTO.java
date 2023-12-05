package br.unipar.api.ApiPillTime.model.dto;

import br.unipar.api.ApiPillTime.model.TipoUsuario;
import br.unipar.api.ApiPillTime.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdosoComCuidadorDTO {

    private Long idosoId;
    private String nomeIdoso;
    private Long cuidadorId;

    @JsonIgnore
    private UserRole role;
    @JsonIgnore
    private TipoUsuario tipoUsuario = TipoUsuario.I;


}
