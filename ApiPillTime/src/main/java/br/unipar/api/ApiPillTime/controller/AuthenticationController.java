package br.unipar.api.ApiPillTime.controller;


import javax.validation.Valid;


import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.PessoaRepository;
import br.unipar.api.ApiPillTime.service.AuthService;
import br.unipar.api.ApiPillTime.service.UsuarioService;
import br.unipar.api.ApiPillTime.user.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/auth")
@Api(tags = "Api Authentication", description = "Autenticação")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    AuthService authService;


    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var usuario = (Usuario) auth.getPrincipal();

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getPessoa().getId(), usuario.getPessoa().getTipoUsuario()));

    }

            @PostMapping("/register")
            public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
                // Verifica se o login já existe.
                if (usuarioService.findByLogin(registerDTO.getLogin()) != null) {
                    return new ResponseEntity<>("Erro: o nome de usuário já está em uso!", HttpStatus.BAD_REQUEST);
                }


                try {
                        usuarioService.registerCuidador(registerDTO);
                    return new ResponseEntity<>("Cuidador Registrado com Sucesso!", HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
    @PostMapping("/register/idoso")
    public ResponseEntity<?> registerIdoso(@RequestBody IdosoDTO idosoDTO) {
        try {
            authService.registerIdoso(idosoDTO);
            return ResponseEntity.ok("Idoso registrado com sucesso e adicionado à lista do cuidador!");
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar idoso: " + e.getMessage());
        }
    }

}



