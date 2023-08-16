package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Endereco;
import br.unipar.api.ApiPillTime.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Endereco insert(@RequestBody Endereco endereco) throws Exception{
        return enderecoService.insert(endereco);
    }
    @PutMapping
    @ApiOperation(value = "Editar um endereço")
    public Endereco update(@RequestBody Endereco endereco) throws Exception{
        return enderecoService.edit(endereco);
    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta um endereço")
    public void delete(@PathVariable Long id) throws Exception{

        enderecoService.delete(id);

    }
    @GetMapping
    @ApiOperation(value = "Retorna uma lista de endereços")
    public List<Endereco> findAll() throws Exception{

        return enderecoService.findAll();

    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um endereço conforme o ID informado")
    public Endereco findById(@PathVariable Long id) throws Exception{

        return enderecoService.findById(id);

    }

}
