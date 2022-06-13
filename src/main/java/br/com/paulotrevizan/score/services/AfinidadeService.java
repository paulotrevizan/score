package br.com.paulotrevizan.score.services;

import br.com.paulotrevizan.score.domains.Afinidade;
import br.com.paulotrevizan.score.dto.AfinidadeDTO;
import br.com.paulotrevizan.score.repositories.AfinidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AfinidadeService {

    @Autowired
    private AfinidadeRepository afinidadeRepository;

    @Transactional
    public Afinidade insert(AfinidadeDTO request) {
        return afinidadeRepository.save(dtoToDomain(request));
    }

    private Afinidade dtoToDomain(AfinidadeDTO dto) {
        return Afinidade.builder()
                .regiao(dto.getRegiao())
                .estados(String.join(";", dto.getEstados()))
                .build();
    }

    public List<String> getEstadosByRegiao(String regiao) {
        Afinidade afinidade = afinidadeRepository.findById(regiao).orElse(null);

        return Optional.ofNullable(afinidade)
                .map(Afinidade::getEstados)
                .map(a -> Arrays.asList(a.split(";")))
                .orElse(new ArrayList<>());
    }

}
