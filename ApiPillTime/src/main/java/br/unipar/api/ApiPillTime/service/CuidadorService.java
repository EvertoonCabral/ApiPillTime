package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.repository.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuidadorService {
    @Autowired
    CuidadorRepository cuidadorRepository;

    public Cuidador insert(Cuidador cuidador) throws Exception{

        //criar metodo de validação conforme as regras de negocio

        cuidadorRepository.saveAndFlush(cuidador);
        return cuidador;

    }
    public Cuidador edit(Cuidador cuidador) throws Exception{

        //CRiar metodo de validação de edição conforme as regas de negocio

        cuidadorRepository.saveAndFlush(cuidador);
        return cuidador;

    }
    public void remove(Long id) throws Exception{
        Cuidador cuidador = findByid(id);
        cuidador.setStAtivo(false);
        cuidadorRepository.saveAndFlush(cuidador);
    }
public List<Cuidador> findAll(){
        return cuidadorRepository.findAll();
}
public Cuidador findByid(Long id) throws Exception{

    Optional<Cuidador> retorno = cuidadorRepository.findById(id);

    if (retorno.isPresent())
        return retorno.get();
     else
        throw new Exception("Marca com Id "+id+" Não Identificada");
}
    public List<Cuidador> findByFilters(String nome) {

        return cuidadorRepository.findByNomeContainingAllIgnoringCase(nome);

    }
}
