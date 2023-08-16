package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.MarcaRemedio;
import br.unipar.api.ApiPillTime.service.MarcaRemedioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/MarcaRemedio")
@Api(tags = "API MarcaRemedio", description = "Marca Remedio")
public class MarcaRemedioController {


    private MarcaRemedioService marcaRemedioservice;
    @PostMapping
    @ApiOperation(value = "Adiciona uma nova Marca de Remedio")
    public MarcaRemedio insert(@RequestBody MarcaRemedio marcaRemedio) throws Exception{

        return marcaRemedioservice.insert(marcaRemedio);

    }
    @PutMapping
    @ApiOperation(value = "Atualiza uma Marca de Remedio")
    public MarcaRemedio update(@RequestBody MarcaRemedio marcaRemedio) throws Exception{

        return marcaRemedioservice.edit(marcaRemedio);

    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta uma Marca de Remedio")
    public void delete(@PathVariable Long id) throws Exception{

        marcaRemedioservice.delete(id);

    }

    @GetMapping
    @ApiOperation("Retorna uma lista de todos os pessoa")
    public List<MarcaRemedio> findAll()throws Exception{
        return marcaRemedioservice.findAll();
    }


    @GetMapping(path ="/{id}" )
    @ApiOperation(value = "Obter uma pessoa pelo seu ID")
    public MarcaRemedio findById(@PathVariable Long id)throws Exception{

        return marcaRemedioservice.findById(id);

    }
    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter uma pessoa pelo seu nome")
    public List<MarcaRemedio> findByFilters(@RequestParam("nome") String nome){

        return marcaRemedioservice.findByFilters(nome);
    }


}
