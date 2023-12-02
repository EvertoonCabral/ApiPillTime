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
    public ResponseEntity<Foto> uploadFoto(@RequestParam("file") MultipartFile file,
                                           @RequestParam("remedioId") Long remedioId) throws IOException {
        Foto fotoSalva = fotoService.salvarFoto(file, remedioId);
        return ResponseEntity.ok(fotoSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foto> getFotoById(@PathVariable Long id) {
        return fotoService.buscarFotoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Foto>> getAllFotos() {
        List<Foto> fotos = fotoService.listarTodasFotos();
        return ResponseEntity.ok(fotos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foto> updateFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        Foto fotoAtualizada = fotoService.atualizarFoto(id, file);
        return ResponseEntity.ok(fotoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoto(@PathVariable Long id) {
        fotoService.deletarFoto(id);
        return ResponseEntity.ok().build();
    }
}
