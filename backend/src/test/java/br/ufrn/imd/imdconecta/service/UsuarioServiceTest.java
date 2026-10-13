package br.ufrn.imd.imdconecta.service;

import br.ufrn.imd.imdconecta.entity.Usuario;
import br.ufrn.imd.imdconecta.exception.EmailJaCadastradoException;
import br.ufrn.imd.imdconecta.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveSalvarUsuarioComSenhaCriptografada() {
        when(repository.existsByEmail("aluno@ufrn.edu.br")).thenReturn(false);
        when(passwordEncoder.encode("senha123")).thenReturn("hash-da-senha");
        when(repository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        Usuario salvo = service.cadastrar("Aluno Teste", "aluno@ufrn.edu.br", "senha123");

        assertEquals("hash-da-senha", salvo.getSenha());
        verify(repository).save(any(Usuario.class));
    }

    @Test
    void deveRejeitarEmailJaCadastrado() {
        when(repository.existsByEmail("aluno@ufrn.edu.br")).thenReturn(true);

        assertThrows(EmailJaCadastradoException.class,
                () -> service.cadastrar("Aluno Teste", "aluno@ufrn.edu.br", "senha123"));

        verify(repository, never()).save(any());
    }
}