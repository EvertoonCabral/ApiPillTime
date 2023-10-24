package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Cuidador;
import br.unipar.api.ApiPillTime.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador,Long> {
@Query
public List<Cuidador> findByNomeContainingAllIgnoringCase(String nome);

    Optional<Cuidador> findById(Long id);

    Cuidador findByEmail(String email);



}
