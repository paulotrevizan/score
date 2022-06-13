package br.com.paulotrevizan.score.auth.configs;

import br.com.paulotrevizan.score.auth.services.JWTService;
import br.com.paulotrevizan.score.auth.services.UserDetailServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    private JWTService jwtService;

    private UserDetailServiceImpl userDetailService;

    public JWTAuthFilter(JWTService jwtService, UserDetailServiceImpl userService) {
        this.jwtService = jwtService;
        this.userDetailService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HEADER_STRING);
        if (authorization != null && authorization.startsWith(TOKEN_PREFIX)) {
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.isTokenValid(token);
            if (isValid) {
                String username = jwtService.getUserName(token);
                UserDetails userDetail = userDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(userDetail, null,
                                userDetail.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(request, response);
    }

}
