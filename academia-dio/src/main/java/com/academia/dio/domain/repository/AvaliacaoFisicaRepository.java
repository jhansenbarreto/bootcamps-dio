package com.academia.dio.domain.repository;

import com.academia.dio.domain.model.AvaliacaoFisica;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jhansen Barreto
 */
@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {

    @Query("FROM AvaliacaoFisica af WHERE af.aluno.id = :alunoId AND af.id = :avaliacaoId")
    Optional<AvaliacaoFisica> buscarPorIds(@Param("alunoId") Long alunoId, @Param("avaliacaoId") Long avaliacaoId);
}
