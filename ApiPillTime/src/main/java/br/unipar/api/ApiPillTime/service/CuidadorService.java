package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.dto.CuidadorDTO;
import br.unipar.api.ApiPillTime.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuidadorService {
    @Autowired
    CuidadorRepository cuidadorRepository;
    @Autowired
    EnderecoService enderecoService;

    public Cuidador insert(CuidadorDTO cuidadorDto) throws Exception{

        Cuidador cuidador = convertCuidadorToEntity(cuidadorDto);
        cuidadorRepository.saveAndFlush(cuidador);
        return cuidador;

    }
    public Cuidador edit(Cuidador cuidador) throws Exception{

        return   cuidadorRepository.saveAndFlush(cuidador);

    }
    public void remove(Long id) throws Exception{
        Cuidador cuidador = findById(id);
        cuidador.setStAtivo(false);
        cuidadorRepository.saveAndFlush(cuidador);
    }
    public List<CuidadorDTO> findAll(){

        List<Cuidador> cuidadores = cuidadorRepository.findAll();
        return cuidadores.stream().map(this::convertCuidadorToDto).collect(Collectors.toList());
    }
    public Cuidador findById(Long id) throws Exception{


        Optional<Cuidador> retorno = cuidadorRepository.findById(id);

        if (retorno.isPresent())
            return (retorno.get());
        else
            throw new Exception("Cuidador com Id "+id+" Não Identificado");
    }
    public List<Cuidador> findByFilters(String nome) {

        return cuidadorRepository.findByNomeContainingAllIgnoringCase(nome);

    }

    public CuidadorDTO convertCuidadorToDto(Cuidador cuidador){

        CuidadorDTO dto = new CuidadorDTO();
        dto.setNome(cuidador.getNome());
        dto.setEmail(cuidador.getEmail());
        dto.setSenha(cuidador.getSenha());
        dto.setCpf(cuidador.getCpf());
        dto.setTelefone(cuidador.getTelefone());
        dto.setDataNascimento(cuidador.getDataNascimento());
        dto.setEndereco(enderecoService.convertToDto(cuidador.getEndereco()));

        return dto;

    }

    public Cuidador convertCuidadorToEntity(CuidadorDTO dto){

        Cuidador cuidador = new Cuidador();
        cuidador.setNome(dto.getNome());
        cuidador.setEmail(dto.getEmail());
        cuidador.setSenha(dto.getSenha());
        cuidador.setCpf(dto.getCpf());
        cuidador.setTelefone(dto.getTelefone());
        cuidador.setEndereco(enderecoService.convertToEntity(dto.getEndereco()));
        cuidador.setDataNascimento(dto.getDataNascimento());

        return cuidador;
    }


    public Cuidador validateCuidadorExists(Long cuidadorId) throws Exception {
        Cuidador cuidador = this.findById(cuidadorId);
        if (cuidador == null) {
            throw new EntityNotFoundException("Cuidador não encontrado com o ID fornecido.");
        }
        return cuidador;
    }


}
