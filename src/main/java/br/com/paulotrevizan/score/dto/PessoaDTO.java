package br.com.paulotrevizan.score.dto;

import br.com.paulotrevizan.score.exceptions.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Dados da Pessoa")
public class PessoaDTO {

    @Schema(description = "Nome")
    @NotEmpty(message = ErrorCodes.NOME_REQUIRED_FIELD_NOT_INFORMED)
    private String nome;

    @Schema(description = "Telefone")
    @NotEmpty(message = ErrorCodes.TELEFONE_REQUIRED_FIELD_NOT_INFORMED)
    private String telefone;

    @Schema(description = "Idade")
    @NotNull(message = ErrorCodes.IDADE_REQUIRED_FIELD_NOT_INFORMED)
    private Integer idade;

    @Schema(description = "Cidade")
    @NotEmpty(message = ErrorCodes.CIDADE_REQUIRED_FIELD_NOT_INFORMED)
    private String cidade;

    @Schema(description = "Estado")
    @NotEmpty(message = ErrorCodes.ESTADO_REQUIRED_FIELD_NOT_INFORMED)
    private String estado;

    @Schema(description = "Score")
    @NotNull(message = ErrorCodes.SCORE_REQUIRED_FIELD_NOT_INFORMED)
    @Range(min = 0, max = 1000, message = ErrorCodes.VALIDATE_SCORE_FIELD_MIN_MAX_SIZE)
    private Integer score;

    @Schema(description = "Nome da Região")
    @NotEmpty(message = ErrorCodes.REGIAO_REQUIRED_FIELD_NOT_INFORMED)
    private String regiao;

    @Schema(hidden = true)
    private String scoreDescricao;

    @Schema(hidden = true)
    private List<String> estados;

}
