package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Foto;
import br.unipar.api.ApiPillTime.model.dto.ResponseFotoDTO;
import br.unipar.api.ApiPillTime.repository.AlarmeRepository;
import br.unipar.api.ApiPillTime.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private AlarmeRepository alarmeRepository;

    public ResponseFotoDTO salvarFoto(MultipartFile file) throws IOException {

        Foto foto = new Foto();
        foto.setArquivo(file.getBytes());

        Foto fotoRetorno = fotoRepository.save(foto);

        ResponseFotoDTO responseFotoDTO = new ResponseFotoDTO(fotoRetorno.getId());

        return responseFotoDTO;

    }

    public Foto buscarFotoPorId(Long id) {
        return fotoRepository.findById(id).get();
    }

    public List<Foto> listarTodasFotos() {
        return fotoRepository.findAll();
    }

    public byte[] getFotoByAlarmeId(Long alarmeId) {
        return fotoRepository.findByAlarmeId(alarmeId)
                .map(Foto::getArquivo)
                .orElseThrow(() -> new EntityNotFoundException("Foto não encontrada para o alarme com ID: " + alarmeId));
    }

    public void deletarFoto(Long id) {
        fotoRepository.deleteById(id);
    }
}
