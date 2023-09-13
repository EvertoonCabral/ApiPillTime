package br.unipar.api.ApiPillTime.repository;


import br.unipar.api.ApiPillTime.model.Alarme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmeRepository  extends JpaRepository<Alarme, Long> {
}
