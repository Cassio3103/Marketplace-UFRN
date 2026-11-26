package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioDetailsServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioDetailsService service;

    @Test
    void deveCarregarUsuarioPeloEmail() {
        Usuario usuario = new Usuario("Aluno Teste", "aluno@ufrn.edu.br", "hash-da-senha");
        when(repository.findByEmail("aluno@ufrn.edu.br")).thenReturn(Optional.of(usuario));

        UserDetails detalhes = service.loadUserByUsername("aluno@ufrn.edu.br");

        assertEquals("aluno@ufrn.edu.br", detalhes.getUsername());
        assertEquals("hash-da-senha", detalhes.getPassword());
    }

    @Test
    void deveLancarExcecaoQuandoEmailNaoExiste() {
        when(repository.findByEmail("naoexiste@ufrn.edu.br")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("naoexiste@ufrn.edu.br"));
    }
}