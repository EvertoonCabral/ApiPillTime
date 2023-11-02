package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.dto.UsuarioDTO;
import br.unipar.api.ApiPillTime.service.UsuarioService;
import br.unipar.api.ApiPillTime.user.Usuario;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@Api(tags = "End points responsavel pelo usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.save(usuario);
        UsuarioDTO usuarioDTO = usuarioService.convertToDto(savedUsuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                .map(usuario -> usuarioService.convertToDto(usuario))
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuarioDTOs, HttpStatus.OK);


    }

}
