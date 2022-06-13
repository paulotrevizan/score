package br.com.paulotrevizan.score.repositories;

import br.com.paulotrevizan.score.domains.Score;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface ScoreRepository extends JpaRepository<Score, String> {

    Score findByScoreInicialLessThanEqualAndScoreFinalGreaterThanEqual(Integer scoreInicial,
                                                                       Integer scoreFinal);

}
