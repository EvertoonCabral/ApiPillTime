package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {

}
