package br.com.paulotrevizan.score.auth.services;

import br.com.paulotrevizan.score.auth.domains.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JWTServiceTests {

    @InjectMocks
    private JWTService jwtService;

    private Usuario usuario = Usuario.builder()
            .username("teste")
            .password("123456")
            .build();

    @Test
    final void shouldGerarTokenSuccess() {
        String resp = jwtService.gerarToken(usuario);
        assertNotNull(resp);
    }

    @Test
    final void shouldIsTokenValidSuccess() {
        String token = jwtService.gerarToken(usuario);
        Boolean resp = jwtService.isTokenValid(token);
        assertTrue(resp);
    }

    @Test
    final void shouldGetUsernameSuccess() {
        String token = jwtService.gerarToken(usuario);
        String resp = jwtService.getUserName(token);
        assertEquals(resp, "teste");
    }

}
