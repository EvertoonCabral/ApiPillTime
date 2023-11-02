package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Alarme;
import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.model.dto.AlarmeDTO;
import br.unipar.api.ApiPillTime.model.dto.AlarmeDTOInsert;
import br.unipar.api.ApiPillTime.repository.AlarmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlarmeService {

    private final AlarmeRepository alarmeRepository;
    private final RemedioService remedioService;
    private final IdosoService idosoService;

    @Autowired
    public AlarmeService(AlarmeRepository alarmeRepository, RemedioService remedioService, @Lazy IdosoService idosoService) {
        this.alarmeRepository = alarmeRepository;
        this.remedioService = remedioService;
        this.idosoService = idosoService;
    }


    public Alarme saveAlarmeForIdoso(Long idosoId, Alarme alarme) throws Exception {
        Idoso idoso = idosoService.findById(idosoId);

        if (idoso == null) {
            throw new Exception("Idoso não encontrado!");
        }

        alarme.setIdoso(idoso);
        return alarmeRepository.save(alarme);
    }


    public Alarme insert(AlarmeDTO alarmeDto) throws Exception{

        //criar metodo de validação conforme as regras de negocio

        Alarme alarme = convertToEntity(alarmeDto);
        alarmeRepository.saveAndFlush(alarme);
        return alarme;

    }
    public Alarme edit(Alarme alarme) throws Exception{

        //CRiar metodo de validação de edição conforme as regas de negocio

        alarmeRepository.saveAndFlush(alarme);
        return alarme;

    }
    public void remove(Long id) throws Exception{
        Alarme alarme = findByid(id);
        alarme.setStatusAlarme(false);
        alarmeRepository.saveAndFlush(alarme);
    }
    public List<AlarmeDTO> findAll(){

        List<AlarmeDTO> listaConvertida = new ArrayList<>();

        for (int i = 0; i<alarmeRepository.findAll().size();i++) {

            listaConvertida.add(convertToDTO(alarmeRepository.findAll().get(i)));

        }

        return listaConvertida;
    }
    public Alarme findByid(Long id) throws Exception{

        Optional<Alarme> retorno = alarmeRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Alarme com Id "+id+" Não Identificado");
    }


    public AlarmeDTO convertToDTO(Alarme alarme) {
        AlarmeDTO dto = new AlarmeDTO();
        dto.setTitulo(alarme.getTitulo());
        dto.setDescricao(alarme.getDescricao());
        dto.setRemediosIdosos(alarme.getRemediosIdosos()
                .stream()
                .map(remedioService::convertToDTO) // Aqui você chama o método correto
                .collect(Collectors.toList()));
        dto.setAlarme(alarme.getAlarme());

        return dto;
    }
    public Alarme convertToEntity(AlarmeDTO dto) {
        Alarme alarme = new Alarme();
        alarme.setTitulo(dto.getTitulo());
        alarme.setDescricao(dto.getDescricao());
        alarme.setRemediosIdosos(dto.getRemediosIdosos()
                .stream()
                .map(remedioService::convertToEntity)
                .collect(Collectors.toList()));
        alarme.setAlarme(dto.getAlarme());
        return alarme;
    }


    public AlarmeDTOInsert convertToDtoInsert(Alarme alarme) {
        AlarmeDTOInsert dto = new AlarmeDTOInsert();

        dto.setTitulo(alarme.getTitulo());
        dto.setDescricao(alarme.getDescricao());
        dto.setAlarme(alarme.getAlarme());
        return dto;
    }


}
