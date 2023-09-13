package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.RemedioIdoso;
import br.unipar.api.ApiPillTime.service.PessoaService;
import br.unipar.api.ApiPillTime.service.RemedioIdosoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/remedioidoso")
@Api(tags = "API RemedioIdoso", description = "RemedioIdoso")
public class RemedioIdosoController {

    @Autowired
    private RemedioIdosoService remedioIdosoService;


    @PostMapping
    @ApiOperation(value= "Adicionar um Remedio a lista do Idoso")
    public ResponseEntity<Object> insert(@RequestBody RemedioIdoso remedioIdoso)  {

        try{
            RemedioIdoso remedioIdoso1 = remedioIdosoService.insert(remedioIdoso);
            return ResponseEntity.status(HttpStatus.CREATED).body(remedioIdoso1);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiErrorMessage(ex.getMessage()));
        }

    }

    @PutMapping
    @ApiOperation(value = "Edita um Remedio da Lista do Idoso")
    public ResponseEntity<Object> edit(@RequestBody RemedioIdoso remedioIdoso) {

        try{
            RemedioIdoso remedioIdoso1 = remedioIdosoService.edit(remedioIdoso);
            return ResponseEntity.ok(remedioIdoso1);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiErrorMessage(ex.getMessage()));
        }


    }
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta um um remedio da Lista do Idoso")
    public ResponseEntity<Object> delete(@PathVariable Long id) {

        try {
            remedioIdosoService.remove(id);
            return ResponseEntity.noContent().build();

        }catch (Exception ex){
            return  ResponseEntity.badRequest().body(new ApiErrorMessage(ex.getMessage()));
        }
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de todos os pessoa")
    public ResponseEntity<List<RemedioIdoso>> findAll(){

        List<RemedioIdoso> remedioIdosos = remedioIdosoService.findAll();
        return ResponseEntity.ok(remedioIdosos);
    }


    @GetMapping(path ="/{id}" )
    @ApiOperation(value = "Obter uma pessoa pelo seu ID")
    public ResponseEntity<Object> findById(@PathVariable Long id) {

        try{
            RemedioIdoso remedioIdoso = remedioIdosoService.findById(id);
            return ResponseEntity.ok(remedioIdoso);

        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }


    }



}
