package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.RemedioIdoso;
import br.unipar.api.ApiPillTime.repository.PessoaRepository;
import br.unipar.api.ApiPillTime.repository.RemedioIdosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemedioIdosoService {


    @Autowired
    private RemedioIdosoRepository remedioIdosoRepository;
    public RemedioIdoso insert(RemedioIdoso remedioIdoso) throws Exception{

        //validar
        remedioIdosoRepository.saveAndFlush(remedioIdoso);

        return remedioIdoso;

    }


    public RemedioIdoso edit(RemedioIdoso remedioIdoso) throws Exception{

        //validar edição
        remedioIdosoRepository.saveAndFlush(remedioIdoso);
        return remedioIdoso;
    }

    public void remove(Long id) throws Exception{

        RemedioIdoso remedioIdoso = findById(id);
        remedioIdoso.setStAtivo(false);
        remedioIdosoRepository.saveAndFlush(remedioIdoso);

    }


    public List<RemedioIdoso> findAll(){

        return remedioIdosoRepository.findAll();


    }

    public RemedioIdoso findById(Long id) throws Exception{

        Optional<RemedioIdoso> retorno = remedioIdosoRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Marca com Id "+id+" Não Identificada");

    }

}
