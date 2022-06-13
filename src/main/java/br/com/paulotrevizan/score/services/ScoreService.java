package br.com.paulotrevizan.score.services;

import br.com.paulotrevizan.score.domains.Score;
import br.com.paulotrevizan.score.dto.ScoreDTO;
import br.com.paulotrevizan.score.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public Score insert(ScoreDTO request) {
        return scoreRepository.save(dtoToDomain(request));
    }

    private Score dtoToDomain(ScoreDTO dto) {
        return Score.builder()
                .descricao(dto.getScoreDescricao())
                .scoreInicial(dto.getScoreInicial())
                .scoreFinal(dto.getScoreFinal())
                .build();
    }

    public String getScoreDescricaoByScoreValue(Integer scoreValue) {
        Score score = scoreRepository.findByScoreInicialLessThanEqualAndScoreFinalGreaterThanEqual(scoreValue,
                scoreValue);

        return Optional.ofNullable(score)
                .map(Score::getDescricao)
                .orElse("Indeterminado");
    }

}
