package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.service.IdosoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/idoso")
@Api(tags = "API idoso", description = "Idoso")
public class IdososController {
    @Autowired
    IdosoService idosoService;

    @PostMapping
    @ApiOperation(value = "Insere um novo usuario do tipo idoso")
    public Idoso insert(@RequestBody Idoso idoso) throws Exception{

        return idosoService.insert(idoso);

    }
    @PutMapping
    @ApiOperation(value = "Editar um idoso")
    public Idoso edit(@RequestBody Idoso idoso) throws Exception{
        return idosoService.update(idoso);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um idoso")
    public void delete(@PathVariable Long id) throws Exception{
       idosoService.remove(id);
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Idosos")
    public List<Idoso> findAll() throws Exception{
        return idosoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um idoso pelo seu ID")
    public Idoso findById(@PathVariable Long id) throws Exception{

        return idosoService.findById(id);

    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um idoso atravez do seu Nome")
    public List<Idoso> findByFillters(@RequestParam("nome")String nome) throws Exception{
        return idosoService.findByFillters(nome);
    }
}
