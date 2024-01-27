package com.academia.dio.domain.repository;

import com.academia.dio.domain.model.Aluno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jhansen Barreto
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a.id FROM Aluno a WHERE a.cpf = :cpf")
    Long buscarIdAlunoPorCPF(@Param("cpf") String cpf);
}
