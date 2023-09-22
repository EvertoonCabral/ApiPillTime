package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Endereco;
import br.unipar.api.ApiPillTime.model.dto.EnderecoDTO;
import br.unipar.api.ApiPillTime.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/endereco")
@Api(tags = "API endereço", description = "endereço")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ApiOperation(value = "Adicionar endereço")
    public ResponseEntity<?> insert(@RequestBody EnderecoDTO enderecoDto) {
        try {
            Endereco endereco = enderecoService.insert(enderecoDto);
            return new ResponseEntity<>(endereco, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @ApiOperation(value = "Editar um endereço")
    public ResponseEntity<?> update(@RequestBody Endereco endereco) {
        try {
            Endereco updatedEndereco = enderecoService.edit(endereco);
            return new ResponseEntity<>(updatedEndereco, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta um endereço")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            enderecoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de endereços")
    public ResponseEntity<?> findAll() {
        try {
            List<Endereco> enderecos = enderecoService.findAll();
            return new ResponseEntity<>(enderecos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um endereço conforme o ID informado")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Endereco endereco = enderecoService.findById(id);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

