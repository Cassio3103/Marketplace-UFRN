package br.ufrn.imd.imdconecta.repository;

import br.ufrn.imd.imdconecta.entity.Anuncio;
import br.ufrn.imd.imdconecta.entity.CategoriaAnuncio;
import br.ufrn.imd.imdconecta.entity.StatusAnuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    List<Anuncio> findByStatusOrderByCriadoEmDesc(StatusAnuncio status);

    List<Anuncio> findByStatusAndCategoriaOrderByCriadoEmDesc(StatusAnuncio status, CategoriaAnuncio categoria);

    List<Anuncio> findByStatusAndDisciplinaIgnoreCaseOrderByCriadoEmDesc(StatusAnuncio status, String disciplina);

    List<Anuncio> findByStatusAndCategoriaAndDisciplinaIgnoreCaseOrderByCriadoEmDesc(
            StatusAnuncio status, CategoriaAnuncio categoria, String disciplina);
}