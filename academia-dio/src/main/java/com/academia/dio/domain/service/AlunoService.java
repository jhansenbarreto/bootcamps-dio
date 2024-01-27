package com.academia.dio.domain.service;

import com.academia.dio.domain.exception.EntidadeNaoEncontradaException;
import com.academia.dio.domain.exception.ErroRegraNegocioException;
import com.academia.dio.domain.model.Aluno;
import com.academia.dio.domain.repository.AlunoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhansen Barreto
 */
@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Não foi possível encontrar um aluno com ID " + id));
    }

    public List<Aluno> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {
        Long id = repository.buscarIdAlunoPorCPF(aluno.getCpf());

        if (id != null && !id.equals(aluno.getId())) {
            throw new ErroRegraNegocioException("CPF duplicado!");
        }
        return repository.save(aluno);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            repository.delete(buscarPorId(id));
            repository.flush();

        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um aluno com ID " + id);
        }
    }
}
