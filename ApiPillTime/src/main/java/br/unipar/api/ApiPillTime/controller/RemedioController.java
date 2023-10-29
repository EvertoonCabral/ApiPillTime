package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.Remedio;
import br.unipar.api.ApiPillTime.model.dto.RemedioDTO;
import br.unipar.api.ApiPillTime.service.RemedioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/remedio")
@Api(tags = "API Remedio", description = "Remedio")
public class
RemedioController {

    @Autowired
    private  RemedioService remedioService;



    @PostMapping
    @ApiOperation(value = "Adicionar um Remedio")
    public ResponseEntity<Object> insert(@RequestBody RemedioDTO remedioDTO) {
        try {


            Remedio novoRemedio = remedioService.insert(remedioDTO);

            String mensagemPersonalizada = "Remédio adicionado com sucesso com o ID: " + novoRemedio.getId();


            return ResponseEntity.status(HttpStatus.CREATED).body(mensagemPersonalizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @PutMapping
    @ApiOperation(value = "Editar um Remedio")
    public ResponseEntity<Object> edit (@RequestBody Remedio remedio) {
        try {
            Remedio remedioEditado = remedioService.edit(remedio);
            return ResponseEntity.ok(remedioEditado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }



    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um remedio")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            remedioService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de todos os remedios")
    public ResponseEntity<List<Remedio>> findAllRemedios() {
        List<Remedio> remedios = remedioService.findAll();
        return ResponseEntity.ok(remedios);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um remedio pelo seu ID")
    public ResponseEntity<Object> findRemedioById(@PathVariable Long id) {
        try {
            Remedio remedio = remedioService.findById(id);
            return ResponseEntity.ok(remedio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um remedio pelo seu nome")
    public ResponseEntity<List<Remedio>> findRemediosByFilters(@RequestParam("nome") String nome) {
        List<Remedio> remedios = remedioService.findByFilters(nome);
        return ResponseEntity.ok(remedios);
    }
}
