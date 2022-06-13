package br.com.paulotrevizan.score.dto;

import br.com.paulotrevizan.score.exceptions.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Dados da Afinidade")
public class AfinidadeDTO {

    @Schema(description = "Nome da Região")
    @NotEmpty(message = ErrorCodes.REGIAO_REQUIRED_FIELD_NOT_INFORMED)
    private String regiao;

    @Schema(description = "Lista de Estados")
    @NotEmpty(message = ErrorCodes.ESTADO_REQUIRED_FIELD_NOT_INFORMED)
    private List<String> estados;

}
