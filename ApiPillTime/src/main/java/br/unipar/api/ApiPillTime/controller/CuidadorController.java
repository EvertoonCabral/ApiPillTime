package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.service.CuidadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cuidador")
@Api(tags = "API Cuidador", description = "Pessoa")
public class CuidadorController {
    @Autowired
    CuidadorService cuidadorService;

    @PostMapping
    @ApiOperation(value = "Adiciona um cuidador")
    public Cuidador insert(@RequestBody Cuidador cuidador) throws Exception{
        return cuidadorService.insert(cuidador);
    }
    @PutMapping
    @ApiOperation(value = "Edita um cuidador")
    public Cuidador edit(@RequestBody Cuidador cuidador) throws Exception{
        return cuidadorService.edit(cuidador);
    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um cuidador")
    public void delete(@PathVariable Long id)throws Exception{

        cuidadorService.remove(id);

    }
    @GetMapping
    @ApiOperation(value = "Retorna uma lista de cuidadores")
    public List<Cuidador> findAll() throws Exception{

        return cuidadorService.findAll();

    }
@GetMapping(path = "/{id}")
@ApiOperation(value = "Retorna um cuidador pelo seu ID")
    public Cuidador findById(@PathVariable Long id)throws Exception{

        return cuidadorService.findByid(id);

    }
    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um cuidador pelo seu nome")
    public List<Cuidador> findByFilters(@RequestParam("nome")String nome) throws Exception{

        return cuidadorService.findByFilters(nome);

    }


}
