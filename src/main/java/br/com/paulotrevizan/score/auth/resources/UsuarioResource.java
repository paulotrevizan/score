package br.com.paulotrevizan.score.auth.resources;

import br.com.paulotrevizan.score.auth.domains.Usuario;
import br.com.paulotrevizan.score.auth.dto.UsuarioDTO;
import br.com.paulotrevizan.score.auth.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api/score/v1/usuarios")
@Tag(name = "Usuários API")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Cadastrar um usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Seu request tem informações ou estrutura inválida"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(
            @Parameter(name = "usuario", required = true) @RequestBody @Valid UsuarioDTO usuario) {
        Usuario response = usuarioService.insert(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Fazer login e gerar um token para as operações", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Seu request tem informações ou estrutura inválida"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDTO> authenticate(@RequestBody @Valid UsuarioDTO usuario) {
        UsuarioDTO response = usuarioService.login(usuario);
        if (Objects.isNull(response)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

}