package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Idosos;
import br.unipar.api.ApiPillTime.service.IdosoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/idoso")
@Api(tags = "API idoso", description = "Idoso")
public class IdososController {
    @Autowired
    IdosoService idosoService;

    @PostMapping
    @ApiOperation(value = "Insere um novo usuario do tipo idoso")
    public Idosos insert(@RequestBody Idosos idosos) throws Exception{

        return idosoService.insert(idosos);

    }
    @PutMapping
    @ApiOperation(value = "Editar um idoso")
    public Idosos edit(@RequestBody Idosos idosos) throws Exception{
        return idosoService.update(idosos);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um idoso")
    public void delete(@PathVariable Long id) throws Exception{
       idosoService.remove(id);
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Idosos")
    public List<Idosos> findAll() throws Exception{
        return idosoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um idoso pelo seu ID")
    public Idosos findById(@PathVariable Long id) throws Exception{

        return idosoService.findById(id);

    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um idoso atravez do seu Nome")
    public List<Idosos> findByFillters(@RequestParam("nome")String nome) throws Exception{
        return idosoService.findByFillters(nome);
    }
}
