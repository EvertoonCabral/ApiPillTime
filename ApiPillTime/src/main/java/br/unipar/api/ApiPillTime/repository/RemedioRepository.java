package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    @Query
    public List<Remedio> findByNomeContainingAllIgnoringCase(String nome);

}
