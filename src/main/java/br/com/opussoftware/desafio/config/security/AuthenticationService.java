package br.com.opussoftware.desafio.config.security;

import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.repository.AuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
    private final AuthorRepository repository;

    public AuthenticationService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Author> author = repository.findByEmail(email);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}
