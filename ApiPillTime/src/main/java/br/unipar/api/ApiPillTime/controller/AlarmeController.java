package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.Alarme;
import br.unipar.api.ApiPillTime.service.AlarmeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/alarme")
@Api(tags = "API Alarme", description = "Alarme")
public class AlarmeController {

    @Autowired
    private AlarmeService alarmeService;

    @PostMapping
    @ApiOperation(value = "Adicionar um Alarme")
    public ResponseEntity<Object> insert(@RequestBody Alarme alarme) {
        try {
            Alarme novoAlarme = alarmeService.insert(alarme);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAlarme);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @PutMapping
    @ApiOperation(value = "Editar um Alarme")
    public ResponseEntity<Object> edit(@RequestBody Alarme alarme) {
        try {
            Alarme alarmeEditado = alarmeService.edit(alarme);
            return ResponseEntity.ok(alarmeEditado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um Alarme")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            alarmeService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de todos os Alarmes")
    public ResponseEntity<List<Alarme>> findAll() {
        List<Alarme> alarmes = alarmeService.findAll();
        return ResponseEntity.ok(alarmes);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um Alarme pelo seu ID")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Alarme alarme = alarmeService.findByid(id);
            return ResponseEntity.ok(alarme);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
