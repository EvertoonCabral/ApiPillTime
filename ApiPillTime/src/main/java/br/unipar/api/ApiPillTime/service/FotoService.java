package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Foto;
import br.unipar.api.ApiPillTime.repository.AlarmeRepository;
import br.unipar.api.ApiPillTime.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private AlarmeRepository alarmeRepository;

    public Foto salvarFoto(MultipartFile file, Long alarmeId) throws IOException {
        var alarme = alarmeRepository.findById(alarmeId)
                .orElseThrow(() -> new RuntimeException("Alarme não encontrado"));

        Foto foto = new Foto();
        foto.setArquivo(file.getBytes());
        foto.setAlarme(alarme);

        return fotoRepository.save(foto);
    }

    public Optional<Foto> buscarFotoPorId(Long id) {
        return fotoRepository.findById(id);
    }

    public List<Foto> listarTodasFotos() {
        return fotoRepository.findAll();
    }

    public Foto atualizarFoto(Long id, MultipartFile file) throws IOException {
        var fotoExistente = buscarFotoPorId(id)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        fotoExistente.setArquivo(file.getBytes());
        return fotoRepository.save(fotoExistente);
    }

    public void deletarFoto(Long id) {
        fotoRepository.deleteById(id);
    }
}
