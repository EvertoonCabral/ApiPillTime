package br.unipar.api.ApiPillTime.service;

import br.unipar.api.ApiPillTime.model.Remedio;
import br.unipar.api.ApiPillTime.model.dto.RemedioDTO;
import br.unipar.api.ApiPillTime.repository.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RemedioService {
    @Autowired
    private RemedioRepository remedioRepository;

    public Remedio insert(RemedioDTO remedioDTO) throws Exception{

        Remedio remedio = convertToEntity(remedioDTO);
        remedioRepository.saveAndFlush(remedio);

    return remedio;

    }


    public Remedio edit(Remedio remedio) throws Exception{

        //validar edição
        remedioRepository.saveAndFlush(remedio);
        return remedio;
    }

   public void remove(Long id) throws Exception{

        Remedio remedio = findById(id);
        remedio.setStAtivo(false);
        remedioRepository.saveAndFlush(remedio);

   }


    public List<Remedio> findAll(){

        return remedioRepository.findAll();


    }

    public Remedio findById(Long id) throws Exception{

        Optional<Remedio> retorno = remedioRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Marca com Id "+id+" Não Identificada");

    }

    public List<Remedio> findByFilters(String nome) {

        return remedioRepository.findByNomeContainingAllIgnoringCase(nome);

    }


    public List<RemedioDTO> findRemediosByCuidadorId(Long cuidadorID) throws Exception{

        List<Remedio> remedios = remedioRepository.findByCuidadorId(cuidadorID);
        return remedios.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    public RemedioDTO convertToDTO(Remedio remedio) {
        RemedioDTO dto = new RemedioDTO();
        dto.setNome(remedio.getNome());
        dto.setMarcaRemedio(remedio.getMarcaRemedio());
        dto.setDosagem(remedio.getDosagem());
        dto.setFormaFarmaceutico(remedio.getFormaFarmaceutico());
        dto.setDataValidade(remedio.getDataValidade());
        dto.setObservacoes(remedio.getObservacoes());
        return dto;
    }

    public Remedio convertToEntity(RemedioDTO dto) {
        Remedio remedio = new Remedio();
        remedio.setNome(dto.getNome());
        remedio.setMarcaRemedio(dto.getMarcaRemedio());
        remedio.setDosagem(dto.getDosagem());
        remedio.setFormaFarmaceutico(dto.getFormaFarmaceutico());
        remedio.setDataValidade(dto.getDataValidade());
        remedio.setObservacoes(dto.getObservacoes());
        return remedio;
    }


}
