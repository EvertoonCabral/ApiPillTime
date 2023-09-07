package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Idoso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IdosoRepository extends JpaRepository<Idoso, Long> {

    @Query
    public List<Idoso> findByNomeContainingAllIgnoringCase(String nome);

}
