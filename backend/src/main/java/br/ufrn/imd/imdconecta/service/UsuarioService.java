package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
    }

    public Usuario cadastrar(String nome, String email, String senha) {
        return null;
    }
}