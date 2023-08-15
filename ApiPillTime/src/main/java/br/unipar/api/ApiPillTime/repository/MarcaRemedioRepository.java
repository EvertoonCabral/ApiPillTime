package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.MarcaRemedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRemedioRepository extends JpaRepository<MarcaRemedio, Long>{

    @Query
    public List<MarcaRemedio> findByNomeContainingAllIgnoringCase(String nome);


}
