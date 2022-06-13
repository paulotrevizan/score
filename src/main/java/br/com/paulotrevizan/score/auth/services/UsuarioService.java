package br.com.paulotrevizan.score.auth.services;

import br.com.paulotrevizan.score.auth.domains.Usuario;
import br.com.paulotrevizan.score.auth.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JWTService jwtService;

    public Usuario insert(UsuarioDTO usuario) {
        return userDetailService.create(createUsuarioDTOPasswordEncoded(usuario));
    }

    private UsuarioDTO createUsuarioDTOPasswordEncoded(UsuarioDTO usuario) {
        return UsuarioDTO.builder()
                .username(usuario.getUsername())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .build();
    }

    public UsuarioDTO login(UsuarioDTO request) {
        boolean isAuthenticated = userDetailService.authenticate(request);
        if (!isAuthenticated) {
            return null;
        }

        String token = jwtService.gerarToken(dtoToDomain(request));

        return UsuarioDTO.builder()
                .username(request.getUsername())
                .token(token)
                .build();
    }

    private Usuario dtoToDomain(UsuarioDTO dto) {
        return Usuario.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

}
