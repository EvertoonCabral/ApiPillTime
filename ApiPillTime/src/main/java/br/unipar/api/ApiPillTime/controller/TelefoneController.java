package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Telefone;
import br.unipar.api.ApiPillTime.service.TelefoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/telefone")
@Api(tags = "API Telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;


    @PostMapping
    @ApiOperation(value = "Adicionar um telefone")
    public Telefone insert(@RequestBody Telefone telefone) throws Exception{

        return telefoneService.insert(telefone);

    }
    @PutMapping
    @ApiOperation(value = "Editar um telefone")
    public Telefone update(@RequestBody Telefone telefone) throws Exception{

        return telefoneService.edit(telefone);

    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Exclui um Telefone")
    public void delete(@PathVariable Long id) throws Exception{

        telefoneService.remove(id);

    }
    @GetMapping
    @ApiOperation(value = "Lista todos os Telefones")
    public List<Telefone> findAll()throws Exception{

        return telefoneService.findAll();

    }



    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um telefone pelo ID")
    public Telefone findById(@PathVariable Long id) throws Exception{

        return telefoneService.findById(id);

    }


}
