package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.service.AuthorizationService;
import br.unipar.api.ApiPillTime.service.PessoaService;
import br.unipar.api.ApiPillTime.user.AuthenticationDTO;
import br.unipar.api.ApiPillTime.user.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
@Api(tags = "Autenticação", description = "Endpoints para operações relacionadas a autentição de usuários")
public class AuthenticationController {

  @Autowired
    private AuthenticationManager authenticationManager;


    @ApiOperation(value = "Realiza Login na Aplicação")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();

    }

}
