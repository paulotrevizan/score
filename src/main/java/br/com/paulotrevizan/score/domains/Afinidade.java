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
@Table(name = "afinidade")
public class Afinidade {

    @Id
    @Column(name = "regiao")
    private String regiao;

    @Column(name = "estados")
    private String estados;

}
