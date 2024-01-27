package com.academia.dio.domain.repository;

import com.academia.dio.domain.model.Matricula;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jhansen Barreto
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
    List<Matricula> findByAlunoId(Long alunoId);
    
    @Query("FROM Matricula m WHERE m.aluno.id = :alunoId AND m.ativa = true")
    Optional<Matricula> findAtivaByAlunoId(Long alunoId);
    
    List<Matricula> findByDataMatriculaBetween(LocalDateTime data1, LocalDateTime data2);
}
