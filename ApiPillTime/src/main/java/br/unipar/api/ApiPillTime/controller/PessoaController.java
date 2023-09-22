package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.dto.PessoaInsertDTO;
import br.unipar.api.ApiPillTime.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoa")
@Api(tags = "API Pessoa", description = "Pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @PostMapping
    @ApiOperation(value= "Adicionar um Pessoa")
    public ResponseEntity<Object> insert(@RequestBody PessoaInsertDTO pessoaInsertDTO)  {

        try{
              Pessoa p1 = pessoaService.insert(pessoaInsertDTO);
              return ResponseEntity.status(HttpStatus.CREATED).body(p1);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiErrorMessage(ex.getMessage()));
        }

    }

    @PutMapping
    @ApiOperation(value = "Editar um Pessoa")
    public ResponseEntity<Object> edit(@RequestBody Pessoa pessoa) {

        try{
            Pessoa p1 = pessoaService.edit(pessoa);
            return ResponseEntity.ok(p1);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiErrorMessage(ex.getMessage()));
        }


    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um pessoa")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

       try {
           pessoaService.remove(id);
           return ResponseEntity.noContent().build();

       }catch (Exception ex){
           return  ResponseEntity.badRequest().body(new ApiErrorMessage(ex.getMessage()));
       }
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de todos os pessoa")
    public ResponseEntity<List<Pessoa>> findAll(){

        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }


    @GetMapping(path ="/{id}" )
    @ApiOperation(value = "Obter uma pessoa pelo seu ID")
    public ResponseEntity<Object> findById(@PathVariable Long id) {

        try{
            Pessoa p1 = pessoaService.findById(id);
            return ResponseEntity.ok(p1);

        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }


    }
    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter uma pessoa pelo seu nome")
    public ResponseEntity<List<Pessoa>> findByFilters(@RequestParam("nome") String nome){
        List<Pessoa> p1 = pessoaService.findByFilters(nome);
        return ResponseEntity.ok(p1);
    }


}
