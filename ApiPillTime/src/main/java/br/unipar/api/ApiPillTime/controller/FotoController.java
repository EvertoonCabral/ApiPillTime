package br.unipar.api.ApiPillTime.controller;

import br.unipar.api.ApiPillTime.model.Foto;
import br.unipar.api.ApiPillTime.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private FotoService fotoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFoto(@RequestParam("file") MultipartFile file) throws IOException {
       return ResponseEntity.ok(fotoService.salvarFoto(file));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Foto> getFotoById(@PathVariable Long id) {
        return ResponseEntity.ok(fotoService.buscarFotoPorId(id));

    }

    @GetMapping
    public ResponseEntity<List<Foto>> getAllFotos() {
        List<Foto> fotos = fotoService.listarTodasFotos();
        return ResponseEntity.ok(fotos);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoto(@PathVariable Long id) {
        fotoService.deletarFoto(id);
        return ResponseEntity.ok().build();
    }
}
