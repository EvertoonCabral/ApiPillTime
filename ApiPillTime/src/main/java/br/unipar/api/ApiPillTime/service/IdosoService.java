package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.model.dto.IdosoDTO;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdosoService {
    @Autowired
    IdosoRepository idosoRepository;
    @Autowired
    EnderecoService enderecoService;


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
    public List<Idoso> findByFillters(String nome){
        return idosoRepository.findByNomeContainingAllIgnoringCase(nome);
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
