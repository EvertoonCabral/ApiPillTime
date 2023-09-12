package br.unipar.api.ApiPillTime.repository;

import br.unipar.api.ApiPillTime.model.RemedioIdoso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedioIdosoRepository extends JpaRepository<RemedioIdoso, Long> {


}
