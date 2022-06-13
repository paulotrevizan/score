package br.com.paulotrevizan.score.auth.services;

import br.com.paulotrevizan.score.auth.configs.UsuarioPasswordEncoder;
import br.com.paulotrevizan.score.auth.domains.Usuario;
import br.com.paulotrevizan.score.auth.dto.UsuarioDTO;
import br.com.paulotrevizan.score.auth.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final String USERNAME_OR_PASSWORD_INVALID = "Usuário não encontrado ou senha inválida";

    @Autowired
    private UsuarioPasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario create(UsuarioDTO request) {
        return usuarioRepository.save(dtoToDomain(request));
    }

    private Usuario dtoToDomain(UsuarioDTO dto) {
        return Usuario.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public boolean authenticate(UsuarioDTO usuario) {
        UserDetails user = loadUserByUsername(usuario.getUsername());

        boolean passwordMatch = encoder.passwordEncoder()
                .matches(usuario.getPassword(), user.getPassword());

        if (passwordMatch) {
            return Boolean.TRUE;
        }

        throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
    }

}