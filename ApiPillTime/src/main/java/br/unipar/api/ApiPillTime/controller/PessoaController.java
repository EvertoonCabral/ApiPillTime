package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoa")
@Api(tags = "API Pessoa", description = "Pessoa")
public class PessoaController {

    private PessoaService pessoaService;


    @PostMapping
    @ApiOperation(value= "Adicionar um Pessoa")
    public Pessoa insert(@RequestBody Pessoa pessoa) throws Exception{

        return pessoaService.insert(pessoa);

    }

    @PutMapping
    @ApiOperation(value = "Editar um Pessoa")
    public Pessoa edit(@RequestBody Pessoa pessoa)throws Exception{

        return pessoaService.edit(pessoa);
    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um pessoa")
    public void delete(@PathVariable Long id)throws Exception{

        pessoaService.remove(id);

    }

    @GetMapping
    @ApiOperation("Retorna uma lista de todos os pessoa")
    public List<Pessoa> findAll(){
        return pessoaService.findAll();
    }


    @GetMapping(path ="/{id}" )
    @ApiOperation(value = "Obter um remedio pelo seu ID")
    public Pessoa findById(@PathVariable Long id)throws Exception{

        return pessoaService.findById(id);

    }
    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter uma pessoa pelo seu nome")
    public List<Pessoa> findByFilters(@RequestParam("nome") String nome){

        return pessoaService.findByFilters(nome);
    }


}
