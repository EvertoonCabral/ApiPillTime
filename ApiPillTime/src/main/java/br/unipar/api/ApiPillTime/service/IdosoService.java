package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Alarme;
import br.unipar.api.ApiPillTime.model.Foto;
import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.model.dto.AlarmeDTOInsert;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.AlarmeRepository;
import br.unipar.api.ApiPillTime.repository.FotoRepository;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdosoService {
    @Autowired
    IdosoRepository idosoRepository;
    @Autowired
    EnderecoService enderecoService;

    @Autowired
    AlarmeRepository alarmeRepository;

    @Autowired
    FotoRepository fotoRepository;


    @Autowired
    FotoService fotoService;

    private AlarmeService alarmeService;

    @Autowired
    public void setAlarmeService(AlarmeService alarmeService) {
        this.alarmeService = alarmeService;
    }

    public Idoso insert(IdosoDTO idosoDto) throws Exception{

        Idoso idoso = convertToEntity(idosoDto);
        idosoRepository.saveAndFlush(idoso);
        return idoso;

    }
    public Idoso updateIdosoDto(IdosoDTO idosoDTO) throws Exception{
        //validar

        Idoso idoso = convertToEntity(idosoDTO);
        idosoRepository.saveAndFlush(idoso);

        return idoso;
    }
    public Idoso update(Idoso idoso) throws Exception{
        //validar

        idosoRepository.saveAndFlush(idoso);

        return idoso;
    }

    public void remove(Long id) throws Exception{

        Idoso idoso = findById(id);
        idoso.setStAtivo(false);
        idosoRepository.saveAndFlush(idoso);

    }

    public List<Idoso> findAll(){

        return idosoRepository.findAll();

    }
    public Idoso findById(Long id) throws Exception{
        Optional<Idoso> retorno = idosoRepository.findById(id);

        if(retorno.isPresent()){
            return       retorno.get();
        }else{
            throw new Exception("Marca com Id "+id+" Não Identificada");
        }
    }

    public Idoso findByCpf(String cpf) throws Exception {
        return idosoRepository.findByCpf(cpf)
                .orElseThrow(() -> new Exception("Idoso com cpf " + cpf + " não encontrado."));
    }



    public List<Idoso> findByFillters(String nome){
        return idosoRepository.findByNomeContainingAllIgnoringCase(nome);
    }


    public Idoso addAlarmeToIdoso(Long idosoId, AlarmeDTOInsert alarmeDtoInsert) throws Exception {
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new Exception("Idoso não encontrado"));

        Alarme alarme = new Alarme();
        alarme.setTitulo(alarmeDtoInsert.getTitulo());
        alarme.setDescricao(alarmeDtoInsert.getDescricao());
        alarme.setAlarme(alarmeDtoInsert.getAlarme());
        alarme.setIdFoto(alarmeDtoInsert.getIdFoto());
        alarme.setIdoso(idoso);
        alarme.setStatusAlarme(true);

        // Primeiro, salve o alarme para gerar um ID
        Alarme alarmeSalvo = alarmeRepository.save(alarme);

        // Agora, se tiver uma foto, associe-a ao alarme
        if (alarmeDtoInsert.getIdFoto() != null) {
            Foto foto = fotoService.buscarFotoPorId(alarmeDtoInsert.getIdFoto());
            if (foto != null) {
                foto.setAlarme(alarmeSalvo);
                fotoRepository.save(foto);
                alarmeSalvo.setIdFoto(foto.getId());
            }
        }

        idoso.getAlarmesIdoso().add(alarmeSalvo);
        idosoRepository.save(idoso);

        return idoso;
    }



    public List<AlarmeDTOInsert> findAlarmesDtoByIdoso(Long idosoId) throws Exception {
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new Exception("Idoso não encontrado"));

        return idoso.getAlarmesIdoso().stream().map(alarme -> {
            AlarmeDTOInsert dto = new AlarmeDTOInsert();
            dto.setTitulo(alarme.getTitulo());
            dto.setDescricao(alarme.getDescricao());
            dto.setAlarme(alarme.getAlarme());
            dto.setIdFoto(alarme.getIdFoto());
            return dto;
        }).collect(Collectors.toList());
    }


    public Idoso convertToEntity(IdosoDTO dto) {

        Idoso idoso = new Idoso();
        idoso.setNome(dto.getNome());
        idoso.setCpf(dto.getCpf());
        idoso.setDataNascimento(dto.getDataNascimento());
        idoso.setEndereco(enderecoService.convertToEntity( dto.getEndereco()));
        idoso.setTelefone(dto.getTelefone());
        idoso.setObservacao(dto.getObservacao());

        return idoso;

    }
    public IdosoDTO convertIdosoToDto(Idoso idoso) {
        IdosoDTO dto = new IdosoDTO();
        dto.setNome(idoso.getNome());
        dto.setCpf(idoso.getCpf());
        dto.setDataNascimento(idoso.getDataNascimento());
        dto.setTelefone(idoso.getTelefone());
        dto.setObservacao(idoso.getObservacao());
        dto.setEndereco(enderecoService.convertToDto(idoso.getEndereco()));  
        return dto;
    }




}
