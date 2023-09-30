package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.*;
import br.unipar.api.ApiPillTime.model.dto.AlarmeDTO;
import br.unipar.api.ApiPillTime.model.dto.CuidadorDTO;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.model.dto.RemedioDTO;
import br.unipar.api.ApiPillTime.service.AlarmeService;
import br.unipar.api.ApiPillTime.service.CuidadorService;
import br.unipar.api.ApiPillTime.service.IdosoService;
import br.unipar.api.ApiPillTime.service.RemedioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    @Autowired
    AlarmeService alarmeService;

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
            cuidadorService.edit(cuidador);

            return ResponseEntity.ok(cuidadorService.convertCuidadorToDto(cuidador));

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
            List<CuidadorDTO> cuidadores = cuidadorService.findAll();
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
            return ResponseEntity.ok(cuidadorService.convertCuidadorToDto(cuidador));
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
            return ResponseEntity.ok((cuidadores));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiErrorMessage(e.getMessage()));
        }
    }

//validado
    @PostMapping("/{cuidadorId}/adicionar-remedio")
    @ApiOperation(value = "Adicionar um remédio à lista de remédios de um cuidador")
    public ResponseEntity<Object> addRemedioToCuidador(@PathVariable Long cuidadorId, @RequestBody RemedioDTO remedioDTO)   {
        try {

            Cuidador cuidador = cuidadorService.validateCuidadorExists(cuidadorId);

            cuidador.getListaRemedio().add(remedioService.convertToEntity(remedioDTO));

            return ResponseEntity.ok( cuidadorService.edit(cuidador));
            
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }


//validado
     @GetMapping("/{cuidadorId}/remedios")
    @ApiOperation(value = "Listar todos os remédios de um cuidador específico")
    public ResponseEntity<Object> listRemediosByCuidador(@PathVariable Long cuidadorId) {

    try {
        List<RemedioDTO> remedios = remedioService.findRemediosByCuidadorId(cuidadorId);
        return ResponseEntity.ok(remedios);

    } catch (EntityNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorMessage(e.getMessage()));
    }
}



    @PostMapping("/{cuidadorId}/adicionar-idoso")
    @ApiOperation(value = "Adicionar um idoso à lista de idosos de um cuidador")
    public ResponseEntity<Object> addIdosoToCuidador(@PathVariable Long cuidadorId, @RequestBody IdosoDTO idosoDTO) throws Exception {
        Cuidador cuidador = cuidadorService.findById(cuidadorId);

        if (cuidador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));
        }

        try {
            cuidador.getListaIdoso().add(idosoService.convertToEntity(idosoDTO));
            Cuidador cuidadorAtualizado = cuidadorService.edit(cuidador);
            return ResponseEntity.ok(cuidadorAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }

    @PostMapping("/{cuidadorId}/idoso/{idosoId}/adicionar-alarme")
    @ApiOperation(value = "Adicionar um alarme à lista de alarmes de um idoso")
    public ResponseEntity<Object> addAlarmeToIdoso(@PathVariable Long cuidadorId, @PathVariable Long idosoId, @RequestBody AlarmeDTO alarmeDTO) throws Exception {
        Cuidador cuidador = cuidadorService.findById(cuidadorId);
        Idoso idoso = idosoService.findById(idosoId);

        if (cuidador == null || idoso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador ou Idoso não encontrado."));
        }

        try {
            idoso.getAlarmesIdoso().add(alarmeService.convertToEntity(alarmeDTO));
            Idoso idosoAtualizado = idosoService.update(idoso);
            return ResponseEntity.ok(idosoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }



}
