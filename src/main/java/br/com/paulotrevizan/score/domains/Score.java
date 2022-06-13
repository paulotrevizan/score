package br.com.paulotrevizan.score.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "score")
public class Score {

    @Id
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "inicial")
    private Integer scoreInicial;

    @Column(name = "final")
    private Integer scoreFinal;

}
