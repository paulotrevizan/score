package br.com.paulotrevizan.score.auth.dto;

import br.com.paulotrevizan.score.exceptions.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Dados do Usuário")
public class UsuarioDTO {

    @Schema(description = "Nome do Usuário")
    @NotEmpty(message = ErrorCodes.USERNAME_REQUIRED_FIELD_NOT_INFORMED)
    private String username;

    @Schema(description = "Senha")
    @NotEmpty(message = ErrorCodes.PASSWORD_REQUIRED_FIELD_NOT_INFORMED)
    private String password;

    @Schema(description = "Token", hidden = true)
    private String token;

}
