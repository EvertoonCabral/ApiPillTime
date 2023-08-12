package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Remedio;
import br.unipar.api.ApiPillTime.service.RemedioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/remedio")
@Api(tags = "API Remedio", description = "Remedio")
public class
remedioController {

    @Autowired
    private RemedioService remedioService;


    @PostMapping
    @ApiOperation(value= "Adicionar um Remedio")
    public Remedio insert(@RequestBody Remedio remedio) throws Exception{

        return remedioService.insert(remedio);

    }

@PutMapping
@ApiOperation(value = "Editar um Remedio")
    public Remedio edit(@RequestBody Remedio remedio)throws Exception{

        return remedioService.edit(remedio);
    }
@DeleteMapping(path = "/{id}")
@ApiOperation(value = "Deletar um remedio")
public void delete(@PathVariable Long id)throws Exception{

        remedioService.remove(id);

}

@GetMapping
@ApiOperation("Retorna uma lista de todos os remedios")
public List<Remedio> findAll(){
        return remedioService.findAll();
}
@GetMapping(path ="/{id}" )
@ApiOperation(value = "Obter um remedio pelo seu ID")
public Remedio findById(@PathVariable Long id)throws Exception{

        return remedioService.findById(id);

}
    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um remedio pelo seu nome")
    public List<Remedio> findByFilters(@RequestParam("nome") String nome){

        return remedioService.findByFilters(nome);
    }

}
