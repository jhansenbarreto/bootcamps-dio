package com.academia.dio.domain.service;

import com.academia.dio.domain.exception.EntidadeNaoEncontradaException;
import com.academia.dio.domain.exception.ErroRegraNegocioException;
import com.academia.dio.domain.model.Matricula;
import com.academia.dio.domain.repository.MatriculaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhansen Barreto
 */
@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    public List<Matricula> listarPorAlunoId(Long alunoId) {
        return repository.findByAlunoId(alunoId);
    }

    public Matricula buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Não foi possível encontrar uma matrícula com ID " + id));
    }

    public List<Matricula> buscarEntreDatas(LocalDateTime data1, LocalDateTime data2) {
        return repository.findByDataMatriculaBetween(data1, data2);
    }

    @Transactional
    public Matricula matricular(Matricula matricula) {
        var optional = repository.findAtivaByAlunoId(matricula.getAluno().getId());

        if (optional.isEmpty()) {
            return salvar(matricula);
        }
        throw new ErroRegraNegocioException("O aluno informado já está matriculado");
    }

    @Transactional
    public Matricula desmatricular(Long alunoId) {
        var optional = repository.findAtivaByAlunoId(alunoId);

        if (optional.isEmpty()) {
            throw new ErroRegraNegocioException("Não existe matrícula ativa para o aluno informado");
        }

        var matricula = optional.get();
        matricula.setAtiva(Boolean.FALSE);
        matricula.setDataDesmatricula(LocalDateTime.now());

        return salvar(matricula);
    }

    @Transactional
    private Matricula salvar(Matricula matricula) {
        return repository.save(matricula);
    }
}
