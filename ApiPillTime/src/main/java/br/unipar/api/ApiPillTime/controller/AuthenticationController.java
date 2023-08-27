package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.service.AuthorizationService;
import br.unipar.api.ApiPillTime.service.PessoaService;
import br.unipar.api.ApiPillTime.user.AuthenticationDTO;
import br.unipar.api.ApiPillTime.user.RegisterDTO;
import br.unipar.api.ApiPillTime.user.UserRepository;
import br.unipar.api.ApiPillTime.user.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@Api(tags = "Autenticação", description = "Endpoints para operações relacionadas a autentição de usuários")
public class AuthenticationController {

  @Autowired
    private AuthenticationManager authenticationManager;

  @Autowired
  private  UserRepository userRepository;

    @ApiOperation(value = "Realiza Login na Aplicação")
    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();

    }
@PostMapping (path = "/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if(this.userRepository.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Usuario newUser = new Usuario(data.getLogin(), data.getPassword(), data.getRole());


        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();

    }

}
