package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.model.Remedio;
import br.unipar.api.ApiPillTime.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private  PessoaRepository pessoaRepository;
    public Pessoa insert(Pessoa pessoa) throws Exception{

        //validar
       pessoaRepository.saveAndFlush(pessoa);

        return pessoa;

    }


    public Pessoa edit(Pessoa pessoa) throws Exception{

        //validar edição
        pessoaRepository.saveAndFlush(pessoa);
        return pessoa;
    }

    public void remove(Long id) throws Exception{

            Pessoa pessoa = findById(id);
            pessoa.setStAtivo(false);
            pessoaRepository.saveAndFlush(pessoa);

    }


    public List<Pessoa> findAll(){

        return pessoaRepository.findAll();


    }

    public Pessoa findById(Long id) throws Exception{

        Optional<Pessoa> retorno = pessoaRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Marca com Id "+id+" Não Identificada");

    }

    public List<Pessoa> findByFilters(String nome) {

        return pessoaRepository.findByNomeContainingAllIgnoringCase(nome);

    }


}
