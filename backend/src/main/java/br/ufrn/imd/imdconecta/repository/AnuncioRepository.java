package br.ufrn.imd.imdconecta.repository;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
}