package br.unipar.api.ApiPillTime.controller;


import javax.validation.Valid;


import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.repository.PessoaRepository;
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
@Api(tags = "Autenticação", description = "Endpoints para operações relacionadas a autentição de usuários")
public class AuthenticationController {

  @Autowired
    private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository userRepository;

    @Autowired
    private PessoaRepository pessoaRepository; // Supondo que você tenha um repositório para 'Pessoa'

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (userRepository.findByLogin(registerDTO.getLogin()) != null) {
            // O usuário já existe
            return new ResponseEntity<>("Erro: o nome de usuário já está em uso!", HttpStatus.BAD_REQUEST);
        }

        // Supondo que o objeto 'pessoa' no 'registerDTO' já esteja preenchido corretamente
        Pessoa pessoa = registerDTO.getPessoa();
        if (pessoa == null) {
            return new ResponseEntity<>("Erro: os dados da pessoa estão incompletos!", HttpStatus.BAD_REQUEST);
        }

        // Você pode querer fazer mais algumas validações aqui para garantir que 'pessoa' é um 'Cuidador'

        // Criar novo usuário
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(registerDTO.getLogin());
        novoUsuario.setPassword(bCryptPasswordEncoder.encode(registerDTO.getSenha()));
        // Se o papel do usuário não está sendo definido em outra parte do código, você pode defini-lo aqui.
        // novoUsuario.setRole(UserRole.CUIDADOR); // ou outro papel adequado

        // Vincular a Pessoa ao novo usuário
        novoUsuario.setPessoa(pessoa);

            pessoaRepository.save(pessoa);
        userRepository.save(novoUsuario);

        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

}
