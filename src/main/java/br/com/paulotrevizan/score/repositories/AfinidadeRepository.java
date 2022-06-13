package br.com.paulotrevizan.score.repositories;

import br.com.paulotrevizan.score.domains.Afinidade;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface AfinidadeRepository extends JpaRepository<Afinidade, String> {

}
