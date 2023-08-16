package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Endereco;
import br.unipar.api.ApiPillTime.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco insert(Endereco endereco) throws Exception{

       return enderecoRepository.saveAndFlush(endereco);

    }

    public Endereco edit(Endereco endereco) throws Exception{

        return enderecoRepository.saveAndFlush(endereco);
    }


    public void delete(Long id) throws Exception{

        Endereco endereco = findById(id);
        endereco.setStAtivo(false);
        enderecoRepository.saveAndFlush(endereco);

    }

    public List<Endereco> findAll()throws Exception{

        return enderecoRepository.findAll();

    }

    public Endereco findById(Long id)throws Exception{

        Optional<Endereco> retorno = enderecoRepository.findById(id);
        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("O Id "+id+" do endereço não foi Identificado");

    }




}
