package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.*;
import br.unipar.api.ApiPillTime.model.dto.*;
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
import java.util.ArrayList;
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


    @GetMapping("/{cuidadorId}/idosos")
    @ApiOperation(value = "Retorna todos os Idosos da Lista do Cuidador")
    public ResponseEntity<?> getIdososByCuidador(@PathVariable Long cuidadorId) {
        try {
            Cuidador cuidador = cuidadorService.findById(cuidadorId);
            if (cuidador == null) {
                return new ResponseEntity<>(new ApiErrorMessage("Nenhum cuidador encontrado com o ID fornecido."), HttpStatus.NOT_FOUND);
            }

            List<IdosoDTO> idosoDTOList = new ArrayList<>();
            for (Idoso idoso : cuidador.getListaIdoso()) {
                idosoDTOList.add(idosoService.convertIdosoToDto(idoso));  // use seu método de conversão aqui
            }

            if (idosoDTOList.isEmpty()) {
                return new ResponseEntity<>("O cuidador não possui idosos cadastrados em sua lista.", HttpStatus.OK);
            }

            return new ResponseEntity<>(idosoDTOList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorMessage("Ocorreu um erro inesperado ao recuperar a lista de idosos. Por favor, tente novamente mais tarde."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{cuidadorId}/adicionar-remedio")
    public ResponseEntity<Object> addRemedioToCuidador(@PathVariable Long cuidadorId, @RequestBody RemedioDTO remedioDTO) {
        try {
            Cuidador cuidador = cuidadorService.validateCuidadorExists(cuidadorId);

            Remedio remedio = remedioService.convertToEntity(remedioDTO);

            // Verifica se o remédio já está na lista do cuidador antes de adicionar
            if (!cuidador.getListaRemedio().contains(remedio)) {
                remedio = remedioService.insert(remedioService.convertToDTO(remedio)); // Salvando o remédio
                cuidadorService.adicionarRemedioAoCuidador(cuidadorId, remedio); // Adiciona o remédio ao cuidador

                String mensagemPersonalizada = String.format("Remédio '%s' foi adicionado com sucesso ao cuidador '%s'.",
                        remedio.getNome(), cuidador.getNome());
                return ResponseEntity.ok(mensagemPersonalizada);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("O remédio já está na lista do cuidador.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorMessage(e.getMessage()));
        }
    }





    @GetMapping("/{cuidadorId}/remedios")
    @ApiOperation(value = "Listar todos os remédios de um cuidador específico")
    public ResponseEntity<Object> listRemediosByCuidador(@PathVariable Long cuidadorId) {
        try {
            // Encontrando o cuidador
            Cuidador cuidador = cuidadorService.findById(cuidadorId);
            if (cuidador == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));
            }

            List<Remedio> remedios = new ArrayList<>(cuidador.getListaRemedio());

            if (remedios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorMessage("Nenhum remédio encontrado para o cuidador especificado."));
            }

            // Convertendo para DTO antes de enviar.
            List<RemedioDTO> remediosDTO = new ArrayList<>();
            for (Remedio remedio : remedios) {
                remediosDTO.add(remedioService.convertToDTO(remedio));
            }

            return ResponseEntity.ok(remediosDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorMessage(e.getMessage()));
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


    @ApiOperation(value = "Adicionar um alarme à lista de alarmes de um idoso")
    @PostMapping("/{cuidadorId}/idoso/{idosoId}/alarme")
    public ResponseEntity<?> addAlarmeToIdoso(@PathVariable Long cuidadorId,
                                              @PathVariable Long idosoId,
                                              @RequestBody AlarmeDTOInsert alarmeDtoInsert) {
        try {
            Cuidador cuidador = cuidadorService.findById(cuidadorId);
            if (cuidador == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiErrorMessage("Cuidador não encontrado."));
            }

            Idoso idoso = idosoService.findById(idosoId);
            if (idoso == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiErrorMessage("Idoso não encontrado."));
            }

            // Aqui, você chama o método no serviço, passando o DTO do alarme
            Idoso updatedIdoso = idosoService.addAlarmeToIdoso(idosoId, alarmeDtoInsert);


            // Retorna sucesso
            return ResponseEntity.ok("Alarme adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiErrorMessage("Ocorreu um erro ao adicionar o alarme: " + e.getMessage()));
        }
    }


}
