package br.com.paulotrevizan.score.services;

import br.com.paulotrevizan.score.domains.Afinidade;
import br.com.paulotrevizan.score.dto.AfinidadeDTO;
import br.com.paulotrevizan.score.repositories.AfinidadeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AfinidadeServiceTests {

    @InjectMocks
    private AfinidadeService afinidadeService;

    @Mock
    private AfinidadeRepository afinidadeRepository;

    private Afinidade afinidade = Afinidade.builder()
            .regiao("Sudeste")
            .estados("SP;RJ;MG;ES")
            .build();

    private List<String> estados = Arrays.asList("SP", "RJ", "MG", "ES");

    private AfinidadeDTO afinidadeRequest = AfinidadeDTO.builder()
            .regiao("Sudeste")
            .estados(estados)
            .build();

    @Test
    final void shouldInsertSuccess() {
        when(afinidadeRepository.save(any(Afinidade.class))).thenReturn(afinidade);
        Afinidade resp = afinidadeService.insert(afinidadeRequest);
        assertThat(resp.getRegiao(), equalTo("Sudeste"));
    }

    @Test
    final void shouldGetEstadosByRegiaoSuccess() {
        when(afinidadeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(afinidade));
        List<String> resp = afinidadeService.getEstadosByRegiao("Sudeste");
        assertThat(resp, equalTo(estados));
    }

}
