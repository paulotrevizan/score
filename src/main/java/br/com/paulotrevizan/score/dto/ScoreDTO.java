package br.com.paulotrevizan.score.dto;

import br.com.paulotrevizan.score.exceptions.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Dados do Score")
public class ScoreDTO {

    @Schema(description = "Descrição")
    @NotEmpty(message = ErrorCodes.DESCRICAO_REQUIRED_FIELD_NOT_INFORMED)
    private String scoreDescricao;

    @Schema(description = "Score Inicial")
    @NotNull(message = ErrorCodes.SCOREINICIAL_REQUIRED_FIELD_NOT_INFORMED)
    @JsonProperty("inicial")
    private Integer scoreInicial;

    @Schema(description = "Score Final")
    @NotNull(message = ErrorCodes.SCOREFINAL_REQUIRED_FIELD_NOT_INFORMED)
    @JsonProperty("final")
    private Integer scoreFinal;

}
