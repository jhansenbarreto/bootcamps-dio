package com.academia.dio.domain.service;

import com.academia.dio.domain.exception.EntidadeNaoEncontradaException;
import com.academia.dio.domain.exception.ErroRegraNegocioException;
import com.academia.dio.domain.model.AvaliacaoFisica;
import com.academia.dio.domain.repository.AvaliacaoFisicaRepository;
import com.academia.dio.domain.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhansen Barreto
 */
@Service
public class AvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository repository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public AvaliacaoFisica buscarPorIds(Long alunoId, Long avaliacaoId) {
        return repository.buscarPorIds(alunoId, avaliacaoId).orElseThrow(()
                -> new EntidadeNaoEncontradaException(
                        String.format("O aluno de ID %d não possui uma avaliação com ID %d", alunoId, avaliacaoId)));
    }

    @Transactional
    public AvaliacaoFisica salvar(AvaliacaoFisica avaliacao) {
        var matricula = matriculaRepository.findAtivaByAlunoId(avaliacao.getAluno().getId());

        if (matricula.isEmpty()) {
            throw new ErroRegraNegocioException("O aluno informado não tem matrícula ativa para fazer avaliação");
        }
        return repository.save(avaliacao);
    }

    @Transactional
    public void excluir(Long alunoId, Long avaliacaoId) {
        repository.delete(buscarPorIds(alunoId, avaliacaoId));
        repository.flush();
    }
}
