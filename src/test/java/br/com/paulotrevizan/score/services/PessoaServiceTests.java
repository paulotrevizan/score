package br.com.paulotrevizan.score.services;

import br.com.paulotrevizan.score.domains.Pessoa;
import br.com.paulotrevizan.score.dto.PessoaDTO;
import br.com.paulotrevizan.score.repositories.PessoaRepository;
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
class PessoaServiceTests {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private AfinidadeService afinidadeService;

    @Mock
    private ScoreService scoreService;

    private Pessoa pessoa = Pessoa.builder()
            .nome("Fulano de Tal")
            .telefone("(18) 9999-9999")
            .idade(35)
            .cidade("Cidade do Fulano")
            .estado("SP")
            .regiao("Sudeste")
            .score(350)
            .build();

    private PessoaDTO pessoaRequest = PessoaDTO.builder()
            .nome("Fulano de Tal")
            .telefone("(18) 9999-9999")
            .idade(35)
            .cidade("Cidade do Fulano")
            .estado("SP")
            .regiao("Sudeste")
            .score(350)
            .build();

    private List<Pessoa> pessoas = Arrays.asList(pessoa);

    private List<String> estados = Arrays.asList("SP", "RJ", "MG", "ES");

    @Test
    final void shouldInsertSuccess() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        Pessoa resp = pessoaService.insert(pessoaRequest);
        assertThat(resp.getNome(), equalTo("Fulano de Tal"));
    }

    @Test
    final void shouldGetByIdSuccess() {
        when(pessoaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pessoa));
        when(afinidadeService.getEstadosByRegiao(Mockito.anyString())).thenReturn(estados);
        when(scoreService.getScoreDescricaoByScoreValue(Mockito.anyInt())).thenReturn("Aceitável");

        PessoaDTO resp = pessoaService.getById(1);
        assertThat(resp.getNome(), equalTo("Fulano de Tal"));
    }

    @Test
    final void shouldGetAllSuccess() {
        when(pessoaRepository.findAll()).thenReturn(pessoas);
        when(afinidadeService.getEstadosByRegiao(Mockito.anyString())).thenReturn(estados);
        when(scoreService.getScoreDescricaoByScoreValue(Mockito.anyInt())).thenReturn("Aceitável");

        List<PessoaDTO> resp = pessoaService.getAll();
        assertThat(resp.get(0).getNome(), equalTo("Fulano de Tal"));
    }

}
