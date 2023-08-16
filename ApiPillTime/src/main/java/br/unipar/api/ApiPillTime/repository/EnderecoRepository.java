package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
