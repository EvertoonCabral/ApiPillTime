package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Idoso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdosoRepository extends JpaRepository<Idoso, Long> {

    Optional<Idoso> findByCpf(String cpf);

    Optional<Idoso> findByEmail(String email);

    List<Idoso> findByNomeContainingAllIgnoringCase(String nome);

}
