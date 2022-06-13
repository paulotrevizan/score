package br.com.paulotrevizan.score.services;

import br.com.paulotrevizan.score.domains.Score;
import br.com.paulotrevizan.score.dto.ScoreDTO;
import br.com.paulotrevizan.score.repositories.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ScoreServiceTests {

    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private ScoreRepository scoreRepository;

    private Score score = Score.builder()
            .descricao("Aceitável")
            .scoreInicial(501)
            .scoreFinal(700)
            .build();

    private ScoreDTO scoreRequest = ScoreDTO.builder()
            .scoreDescricao("Aceitável")
            .scoreInicial(501)
            .scoreFinal(700)
            .build();

    @Test
    final void shouldInsertSuccess() {
        when(scoreRepository.save(any(Score.class))).thenReturn(score);
        Score resp = scoreService.insert(scoreRequest);
        assertThat(resp.getDescricao(), equalTo("Aceitável"));
    }

    @Test
    final void shouldGetScoreDescricaoByScoreValueSuccess() {
        when(scoreRepository
                .findByScoreInicialLessThanEqualAndScoreFinalGreaterThanEqual(
                        Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(score);
        String resp = scoreService.getScoreDescricaoByScoreValue(650);
        assertThat(resp, equalTo("Aceitável"));
    }

}
