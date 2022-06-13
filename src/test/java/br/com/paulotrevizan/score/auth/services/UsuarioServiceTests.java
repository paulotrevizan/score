package br.com.paulotrevizan.score.auth.services;

import br.com.paulotrevizan.score.auth.domains.Usuario;
import br.com.paulotrevizan.score.auth.dto.UsuarioDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceTests {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UserDetailServiceImpl userDetailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTService jwtService;

    private UsuarioDTO usuarioDTO = UsuarioDTO.builder()
            .username("teste")
            .password("123456")
            .build();

    private Usuario usuario = Usuario.builder()
            .username("teste")
            .password("123456")
            .build();

    @Test
    final void shouldInsertSuccess() {
        when(userDetailService.create(any(UsuarioDTO.class))).thenReturn(usuario);
        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("123456");
        Usuario resp = usuarioService.insert(usuarioDTO);
        assertThat(resp.getUsername(), equalTo(usuarioDTO.getUsername()));
    }

    @Test
    final void shouldLoginSuccess() {
        when(jwtService.gerarToken(any(Usuario.class))).thenReturn("abc123");
        when(userDetailService.authenticate(any(UsuarioDTO.class))).thenReturn(Boolean.TRUE);
        UsuarioDTO resp = usuarioService.login(usuarioDTO);
        assertThat(resp.getToken(), equalTo("abc123"));
    }

}
