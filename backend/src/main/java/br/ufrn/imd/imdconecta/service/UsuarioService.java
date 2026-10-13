package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.exception.EmailJaCadastradoException;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(String nome, String email, String senha) {
        if (repository.existsByEmail(email)) {
            throw new EmailJaCadastradoException(email);
        }
        Usuario usuario = new Usuario(nome, email, passwordEncoder.encode(senha));
        return repository.save(usuario);
    }
}