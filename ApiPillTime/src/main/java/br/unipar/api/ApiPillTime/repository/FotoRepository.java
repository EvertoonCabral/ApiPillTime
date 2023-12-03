package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {

    Optional<Foto> findByAlarmeId(Long alarmeId);


}
