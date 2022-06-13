package br.com.paulotrevizan.score.auth.services;

import br.com.paulotrevizan.score.auth.configs.UsuarioPasswordEncoder;
import br.com.paulotrevizan.score.auth.domains.Usuario;
import br.com.paulotrevizan.score.auth.dto.UsuarioDTO;
import br.com.paulotrevizan.score.auth.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDetailServiceImplTests {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioPasswordEncoder passwordEncoder;

    private UsuarioDTO usuarioDTO = UsuarioDTO.builder()
            .username("teste")
            .password("123456")
            .build();

    private Usuario usuario = Usuario.builder()
            .username("teste")
            .password("123456")
            .build();

    @Test
    final void shouldCreateSuccess() {
        when(usuarioRepository.save(ArgumentMatchers.any(Usuario.class))).thenReturn(usuario);
        Usuario resp = userDetailService.create(usuarioDTO);
        assertThat(resp.getUsername(), equalTo(usuarioDTO.getUsername()));
    }

    @Test
    final void shouldLoadUserByUsernameSuccess() {
        when(usuarioRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(usuario));
        UserDetails resp = userDetailService.loadUserByUsername("teste");
        assertEquals(resp.getUsername(), "teste");
    }

}
