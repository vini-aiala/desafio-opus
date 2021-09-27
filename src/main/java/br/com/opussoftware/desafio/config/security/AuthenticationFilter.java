package br.com.opussoftware.desafio.config.security;

import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.repository.AuthorRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService tokenService;
    private final AuthorRepository repository;

    public AuthenticationFilter(JwtTokenService tokenService, AuthorRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);
        boolean valid = tokenService.isValid(token);

        if (valid) {
            Long id = tokenService.getAuthorId(token);
            Author author = repository.getById(id);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(author, null, author.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7);
    }
}
