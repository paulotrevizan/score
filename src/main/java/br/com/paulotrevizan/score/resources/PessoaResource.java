package br.com.paulotrevizan.score.resources;

import br.com.paulotrevizan.score.domains.Pessoa;
import br.com.paulotrevizan.score.dto.PessoaDTO;
import br.com.paulotrevizan.score.services.PessoaService;
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
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/score/v1/pessoas")
@Tag(name = "Pessoas API")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Cadastrar uma pessoa", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Seu request tem informações ou estrutura inválida"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> insert(
            @Parameter(name = "pessoa", required = true) @Valid @RequestBody PessoaDTO pessoa) {
        Pessoa response = pessoaService.insert(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Buscar uma Pessoa por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Seu request tem informações ou estrutura inválida"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<PessoaDTO> getById(
            @Parameter(name = "id", required = true) @PathVariable(name = "id") Integer id) {
        PessoaDTO response = pessoaService.getById(id);
        if (Objects.isNull(response)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar todas as pessoas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Seu request tem informações ou estrutura inválida"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<PessoaDTO>> getAll() {
        List<PessoaDTO> response = pessoaService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

}
