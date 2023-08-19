package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.Telefone;
import br.unipar.api.ApiPillTime.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public Telefone insert(Telefone telefone) throws Exception{

        telefoneRepository.saveAndFlush(telefone);
        return telefone;

    }
    public Telefone edit(Telefone telefone) throws Exception{
        telefoneRepository.saveAndFlush(telefone);
        return telefone;

    }

    public List<Telefone> findAll(){

        return telefoneRepository.findAll();
    }

    public Telefone findById (Long id) throws Exception{

        Optional<Telefone> retorno = telefoneRepository.findById(id);
        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Marca com Id "+id+" Não Identificada");


    }

    public void remove(Long id) throws Exception{

        Telefone telefone = findById(id);
        telefone.setStAtivo(false);
        telefoneRepository.saveAndFlush(telefone);
    }



}
