package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.MarcaRemedio;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.repository.MarcaRemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaRemedioService {
    @Autowired
    public MarcaRemedioRepository marcaRemedioRepository;

    public MarcaRemedio insert (MarcaRemedio marcaRemedio) throws Exception{
        //Criar metodo de validação
        return marcaRemedioRepository.saveAndFlush(marcaRemedio);

    }

    public MarcaRemedio edit(MarcaRemedio marcaRemedio)throws Exception{

        return marcaRemedioRepository.saveAndFlush(marcaRemedio);

    }

    public void delete(Long id)throws Exception{

        MarcaRemedio marcaRemedio = findById(id);
        marcaRemedio.setStAtivo(false);
        marcaRemedioRepository.saveAndFlush(marcaRemedio);

    }

    public List<MarcaRemedio> findAll() throws Exception{

        return marcaRemedioRepository.findAll();
    }

    public MarcaRemedio findById(Long id) throws Exception{
        Optional<MarcaRemedio> retorno = marcaRemedioRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Marca com Id "+id+" Não Identificada");

    }
    public List<MarcaRemedio> findByFilters(String nome) {

        return marcaRemedioRepository.findByNomeContainingAllIgnoringCase(nome);

    }

}
