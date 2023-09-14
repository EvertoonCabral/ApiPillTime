package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.exception.ApiErrorMessage;
import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.Remedio;
import br.unipar.api.ApiPillTime.service.CuidadorService;
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

        return cuidadorService.findById(id);

    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um cuidador pelo seu nome")
    public List<Cuidador> findByFilters(@RequestParam("nome")String nome) throws Exception{

        return cuidadorService.findByFilters(nome);

    }

        @PostMapping(path = "/{cuidadorId}/adicionar-remedio")
        @ApiOperation(value = "Adicionar um remédio à lista de remédios de um cuidador")
        public ResponseEntity<Object> addRemedioToCuidador(
                @PathVariable Long cuidadorId, @RequestBody Remedio remedio) throws Exception {

            // Obtenha o cuidador pelo ID fornecido
            Cuidador cuidador = cuidadorService.findById(cuidadorId);

            // Se o cuidador não for encontrado, retorne um erro
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

        // ... outros endpoints ...



    @GetMapping("/{cuidadorId}/remedios")
    @ApiOperation(value = "Listar todos os remédios de um cuidador específico")
    public ResponseEntity<Object> listRemediosByCuidador(@PathVariable Long cuidadorId) throws Exception {

        // Obtenha o cuidador pelo ID fornecido
        Cuidador cuidador = cuidadorService.findById(cuidadorId);

        // Se o cuidador não for encontrado, retorne um erro
        if (cuidador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorMessage("Cuidador não encontrado com o ID fornecido."));
        }

        // Retorna a lista de remédios do cuidador
        return ResponseEntity.ok(cuidador.getListaRemedio());
    }





}
