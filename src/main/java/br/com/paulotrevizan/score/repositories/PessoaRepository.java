package br.com.paulotrevizan.score.repositories;

import br.com.paulotrevizan.score.domains.Pessoa;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
