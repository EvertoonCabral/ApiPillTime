package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.model.dto.AlarmeDTOInsert;
import br.unipar.api.ApiPillTime.model.dto.IdosoComCuidadorDTO;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.service.CuidadorService;
import br.unipar.api.ApiPillTime.service.IdosoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;  // Para usar o HashMap no tratamento de exceções.
import java.util.Map;     // Para o tipo de retorno do corpo de resposta do erro.

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/idoso")
@Api(tags = "API Idoso", description = "Idoso")
public class IdososController {
    @Autowired
    IdosoService idosoService;

    @Autowired
    private CuidadorService cuidadorService;



    @PostMapping
    @ApiOperation(value = "Insere um novo usuário do tipo idoso")
    public ResponseEntity<Object> insert(@RequestBody IdosoDTO idosoDto) {
        try {
            Idoso idoso = idosoService.insert(idosoDto);
            IdosoDTO idosoDTO = idosoService.convertIdosoToDto(idoso);

            return new ResponseEntity<>(idosoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @ApiOperation(value = "Editar um idoso")
    public ResponseEntity<Idoso> edit(@RequestBody IdosoDTO idosoDto) {
        try {
            Idoso idoso = idosoService.updateIdosoDto(idosoDto);
            return new ResponseEntity<>(idoso, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletar um idoso")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            idosoService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Idosos")
    public ResponseEntity<List<IdosoDTO>> findAll() {
        try {
            List<Idoso> idosos = idosoService.findAll();
            List<IdosoDTO> idososDTO = idosos.stream()
                    .map(idosoService::convertIdosoToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(idososDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um idoso pelo seu ID")
    public ResponseEntity<IdosoDTO> findById(@PathVariable Long id) {
        try {
            Idoso idoso = idosoService.findById(id);
            IdosoDTO idosoDTO = idosoService.convertIdosoToDto(idoso);
            return new ResponseEntity<>(idosoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um idoso através do seu Nome")
    public ResponseEntity<List<Idoso>> findByFilters(@RequestParam("nome") String nome) {
        try {
            List<Idoso> idosos = idosoService.findByFillters(nome);
            return new ResponseEntity<>(idosos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/alarmes")
    @ApiOperation(value = "Retorna uma lista de alarmes (como DTOs) do idoso específico")
    public ResponseEntity<?> getAlarmesByIdoso(@PathVariable Long id) {
        try {
            List<AlarmeDTOInsert> alarmesDto = idosoService.findAlarmesDtoByIdoso(id);
            return new ResponseEntity<>(alarmesDto, HttpStatus.OK);
        } catch (Exception e) {
            // Tratamento de erro
            Map<String, String> error = new HashMap<>();
            error.put("mensagem", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/cpf/{cpf}")
    @ApiOperation(value = "Retorna um  idoso apartir do CPF")
    public ResponseEntity<IdosoDTO> getIdosoByCpf(@PathVariable String cpf) {
        System.out.println("Buscando idoso com CPF: " + cpf);
        try {
            IdosoDTO idosoDTO = idosoService.convertIdosoToDto(idosoService.findByCpf(cpf));
            return ResponseEntity.ok(idosoDTO);
        } catch (Exception e) {
            System.out.println("Erro ao buscar idoso com CPF: " + cpf + ". Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Em IdososController

    @GetMapping(path = "/full/{id}")
    public ResponseEntity<IdosoComCuidadorDTO> getFullIdoso(@PathVariable Long id) {
        try {
            IdosoComCuidadorDTO idosoDTO = idosoService.findIdosoWithCuidador(id);
            return ResponseEntity.ok(idosoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }





}



