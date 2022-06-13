package br.com.paulotrevizan.score.resources;

import br.com.paulotrevizan.score.domains.Afinidade;
import br.com.paulotrevizan.score.dto.AfinidadeDTO;
import br.com.paulotrevizan.score.services.AfinidadeService;
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

@RestController
@RequestMapping("/api/score/v1/afinidades")
@Tag(name = "Afinidades API")
public class AfinidadeResource {

    @Autowired
    private AfinidadeService afinidadeService;

    @Operation(summary = "Cadastrar uma afinidade", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Seu request tem informações ou estrutura inválida"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> insert(
            @Parameter(name = "afinidade", required = true) @Valid @RequestBody AfinidadeDTO afinidade) {
        Afinidade response = afinidadeService.insert(afinidade);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getRegiao()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
