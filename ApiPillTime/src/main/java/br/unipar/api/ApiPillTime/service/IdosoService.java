package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Idosos;
import br.unipar.api.ApiPillTime.model.Pessoa;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdosoService {
    @Autowired
    IdosoRepository idosoRepository;

    public Idosos insert(Idosos idosos) throws Exception{

        //validar
        idosoRepository.saveAndFlush(idosos);
        return idosos;

    }
    public Idosos update(Idosos idosos) throws Exception{
        //validar
         idosoRepository.saveAndFlush(idosos);

        return idosos;
    }
    public void remove(Long id) throws Exception{

        Idosos idosos = findById(id);
        idosos.setStAtivo(false);
        idosoRepository.saveAndFlush(idosos);

    }

    public List<Idosos> findAll(){

        return idosoRepository.findAll();

    }
public Idosos findById(Long id) throws Exception{
    Optional<Idosos> retorno = idosoRepository.findById(id);

    if(retorno.isPresent()){
    return       retorno.get();
    }else{
        throw new Exception("Marca com Id "+id+" Não Identificada");
    }
}
public List<Idosos> findByFillters(String nome){
        return idosoRepository.findByNomeContainingAllIgnoringCase(nome);
}



}
