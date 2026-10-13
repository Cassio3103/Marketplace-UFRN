package br.ufrn.imd.imdconecta.repository;

import br.ufrn.imd.imdconecta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);
}