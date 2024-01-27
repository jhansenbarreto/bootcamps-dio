package com.academia.dio.api.controller;

import com.academia.dio.api.assembler.AlunoConvert;
import com.academia.dio.api.dto.AlunoInput;
import com.academia.dio.api.dto.AlunoOutput;
import com.academia.dio.domain.service.AlunoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhansen Barreto
 */
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private AlunoConvert convert;

    @GetMapping
    public List<AlunoOutput> listar() {
        return convert.toListOutput(service.listarTodos());
    }

    @GetMapping("/{id}")
    public AlunoOutput buscar(@PathVariable Long id) {
        return convert.toOutput(service.buscarPorId(id));
    }

    @GetMapping("/pesquisar/{nome}")
    public List<AlunoOutput> buscar(@PathVariable String nome) {
        return convert.toListOutput(service.buscarPorNome(nome));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoOutput salvar(@RequestBody @Valid AlunoInput input) {
        return convert.toOutput(service.salvar(convert.toObjectModel(input)));
    }

    @PutMapping("/{id}")
    public AlunoOutput atualizar(@PathVariable Long id, @RequestBody @Valid AlunoInput input) {
        var aluno = service.buscarPorId(id);
        convert.copyToObjectModel(input, aluno);
        return convert.toOutput(service.salvar(aluno));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
