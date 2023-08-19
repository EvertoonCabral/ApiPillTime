package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {


}
