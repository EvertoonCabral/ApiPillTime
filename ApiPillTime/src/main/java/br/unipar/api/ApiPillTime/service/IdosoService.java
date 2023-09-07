package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.repository.IdosoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdosoService {
    @Autowired
    IdosoRepository idosoRepository;

    public Idoso insert(Idoso idoso) throws Exception{

        //validar
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



}
