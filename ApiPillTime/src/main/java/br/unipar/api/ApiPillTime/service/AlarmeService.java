package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Alarme;
import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.Idoso;
import br.unipar.api.ApiPillTime.repository.AlarmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlarmeService {

    @Autowired
    AlarmeRepository alarmeRepository;

    @Autowired
    private IdosoService idosoService;

    public Alarme saveAlarmeForIdoso(Long idosoId, Alarme alarme) throws Exception {
        Idoso idoso = idosoService.findById(idosoId);

        if (idoso == null) {
            throw new Exception("Idoso não encontrado!");
        }

        alarme.setIdoso(idoso);
        return alarmeRepository.save(alarme);
    }


    public Alarme insert(Alarme alarme) throws Exception{

        //criar metodo de validação conforme as regras de negocio

        alarmeRepository.saveAndFlush(alarme);
        return alarme;

    }
    public Alarme edit(Alarme alarme) throws Exception{

        //CRiar metodo de validação de edição conforme as regas de negocio

        alarmeRepository.saveAndFlush(alarme);
        return alarme;

    }
    public void remove(Long id) throws Exception{
        Alarme alarme = findByid(id);
        alarme.setStatusAlarme(false);
        alarmeRepository.saveAndFlush(alarme);
    }
    public List<Alarme> findAll(){
        return alarmeRepository.findAll();
    }
    public Alarme findByid(Long id) throws Exception{

        Optional<Alarme> retorno = alarmeRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Alarme com Id "+id+" Não Identificado");
    }


}
