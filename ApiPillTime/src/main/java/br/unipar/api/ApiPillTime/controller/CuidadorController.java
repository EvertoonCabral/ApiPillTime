package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.*;
import br.unipar.api.ApiPillTime.model.dto.CuidadorDTO;
import br.unipar.api.ApiPillTime.service.AlarmeService;
import br.unipar.api.ApiPillTime.service.CuidadorService;
import br.unipar.api.ApiPillTime.service.IdosoService;
import br.unipar.api.ApiPillTime.service.RemedioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cuidador")
@Api(tags = "API Cuidador", description = "Pessoa")
public class CuidadorController {
    @Autowired
    CuidadorService cuidadorService;
    @Autowired
    RemedioService remedioService;
    @Autowired
    IdosoService idosoService;
    @PostMapping
    @ApiOperation(value = "Adiciona um cuidador")
    public ResponseEntity<?> insert(@RequestBody CuidadorDTO cuidadorDto) {
        try {
            Cuidador savedCuidador = cuidadorService.insert(cuidadorDto);
            return ResponseEntity.ok(savedCuidador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @PutMapping
    @ApiOperation(value = "Edita um cuidador")
    public ResponseEntity<?> edit(@RequestBody Cuidador cuidador) {
        try {
            Cuidador updatedCuidador = cuidadorService.edit(cuidador);
            return ResponseEntity.ok(updatedCuidador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um cuidador")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            cuidadorService.remove(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de cuidadores")
    public ResponseEntity<?> findAll() {
        try {
            List<Cuidador> cuidadores = cuidadorService.findAll();
            return ResponseEntity.ok(cuidadores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um cuidador pelo seu ID")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Cuidador cuidador = cuidadorService.findById(id);
            return ResponseEntity.ok(cuidador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um cuidador pelo seu nome")
    public ResponseEntity<?> findByFilters(@RequestParam("nome") String nome) {
        try {
            List<Cuidador> cuidadores = cuidadorService.findByFilters(nome);
            return ResponseEntity.ok(cuidadores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }


    @PostMapping("/{cuidadorId}/adicionar-remedio")
    @ApiOperation(value = "Adicionar um remédio à lista de remédios de um cuidador")
    public ResponseEntity<Object> addRemedioToCuidador(@PathVariable Long cuidadorId, @RequestBody Remedio remedio) throws Exception {
        Cuidador cuidador = cuidadorService.findById(cuidadorId);

        if (cuidador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));
        }

        try {
            cuidador.getListaRemedio().add(remedio);
            Cuidador cuidadorAtualizado = cuidadorService.edit(cuidador);
            return ResponseEntity.ok(cuidadorAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }



    @GetMapping("/{cuidadorId}/remedios")
    @ApiOperation(value = "Listar todos os remédios de um cuidador específico")
    public ResponseEntity<Object> listRemediosByCuidador(@PathVariable Long cuidadorId) throws Exception {

        Cuidador cuidador = cuidadorService.findById(cuidadorId);

        if (cuidador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));
        }

        // Retorna a lista de remédios do cuidador
        return ResponseEntity.ok(cuidador.getListaRemedio());
    }

    @PostMapping("/{cuidadorId}/adicionar-idoso")
    @ApiOperation(value = "Adicionar um idoso à lista de idosos de um cuidador")
    public ResponseEntity<Object> addIdosoToCuidador(@PathVariable Long cuidadorId, @RequestBody Idoso idoso) throws Exception {
        Cuidador cuidador = cuidadorService.findById(cuidadorId);

        if (cuidador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));
        }

        try {
            cuidador.getListaIdoso().add(idoso);
            Cuidador cuidadorAtualizado = cuidadorService.edit(cuidador);
            return ResponseEntity.ok(cuidadorAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @PostMapping("/{cuidadorId}/idoso/{idosoId}/adicionar-alarme")
    @ApiOperation(value = "Adicionar um alarme à lista de alarmes de um idoso")
    public ResponseEntity<Object> addAlarmeToIdoso(@PathVariable Long cuidadorId, @PathVariable Long idosoId, @RequestBody Alarme alarme) throws Exception {
        Cuidador cuidador = cuidadorService.findById(cuidadorId);
        Idoso idoso = idosoService.findById(idosoId);

        if (cuidador == null || idoso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador ou Idoso não encontrado."));
        }

        try {
            idoso.getAlarmesIdoso().add(alarme);
            Idoso idosoAtualizado = idosoService.update(idoso);
            return ResponseEntity.ok(idosoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }



}
