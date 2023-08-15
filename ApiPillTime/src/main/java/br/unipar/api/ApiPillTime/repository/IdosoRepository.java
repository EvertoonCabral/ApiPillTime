package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Idosos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IdosoRepository extends JpaRepository<Idosos, Long> {

    @Query
    public List<Idosos> findByNomeContainingAllIgnoringCase(String nome);

}
