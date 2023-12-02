package br.unipar.api.ApiPillTime.service;


import br.unipar.api.ApiPillTime.model.Foto;
import br.unipar.api.ApiPillTime.model.Remedio;
import br.unipar.api.ApiPillTime.repository.FotoRepository;
import br.unipar.api.ApiPillTime.repository.RemedioRepository;
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
    private RemedioRepository remedioRepository;

    public Foto salvarFoto(MultipartFile file, Long remedioId) throws IOException {
        Remedio remedio = remedioRepository.findById(remedioId)
                .orElseThrow(() -> new RuntimeException("Remédio não encontrado"));

        Foto foto = new Foto();
        foto.setArquivo(file.getBytes());
        foto.setRemedio(remedio);

        return fotoRepository.save(foto);
    }

    public Optional<Foto> buscarFotoPorId(Long id) {
        return fotoRepository.findById(id);
    }

    public List<Foto> listarTodasFotos() {
        return fotoRepository.findAll();
    }

    public Foto atualizarFoto(Long id, MultipartFile file) throws IOException {
        Foto fotoExistente = buscarFotoPorId(id)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        fotoExistente.setArquivo(file.getBytes());
        return fotoRepository.save(fotoExistente);
    }

    public void deletarFoto(Long id) {
        fotoRepository.deleteById(id);
    }

}
