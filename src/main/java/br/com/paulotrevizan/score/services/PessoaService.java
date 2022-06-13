package br.com.paulotrevizan.score.services;

import br.com.paulotrevizan.score.domains.Pessoa;
import br.com.paulotrevizan.score.dto.PessoaDTO;
import br.com.paulotrevizan.score.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private AfinidadeService afinidadeService;

    @Transactional
    public Pessoa insert(PessoaDTO request) {
        return pessoaRepository.save(dtoToDomain(request));
    }

    private Pessoa dtoToDomain(PessoaDTO dto) {
        return Pessoa.builder()
                .dataInclusao(LocalDate.now())
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .idade(dto.getIdade())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .regiao(dto.getRegiao())
                .score(dto.getScore())
                .build();
    }

    public PessoaDTO getById(Integer id) {
        return domainToResponse(pessoaRepository.findById(id).orElse(null));
    }

    private PessoaDTO domainToResponse(Pessoa pessoa) {
        if (Objects.isNull(pessoa)) {
            return null;
        }

        return PessoaDTO.builder()
                .nome(pessoa.getNome())
                .telefone(pessoa.getTelefone())
                .idade(pessoa.getIdade())
                .cidade(pessoa.getCidade())
                .scoreDescricao(getScoreDescricaoByScoreValue(pessoa.getScore()))
                .estados(getEstadosByRegiao(pessoa.getRegiao()))
                .build();
    }

    private String getScoreDescricaoByScoreValue(Integer score) {
        return scoreService.getScoreDescricaoByScoreValue(score);
    }

    private List<String> getEstadosByRegiao(String regiao) {
        return afinidadeService.getEstadosByRegiao(regiao);
    }

    public List<PessoaDTO> getAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoas.stream()
                .map(this::domainToResponse)
                .collect(Collectors.toList());
    }

}
