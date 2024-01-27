package com.academia.dio.api.controller;

import com.academia.dio.api.assembler.AvaliacaoFisicaConvert;
import com.academia.dio.api.dto.AvaliacaoFisicaInput;
import com.academia.dio.api.dto.AvaliacaoFisicaOutput;
import com.academia.dio.domain.model.AvaliacaoFisica;
import com.academia.dio.domain.service.AlunoService;
import com.academia.dio.domain.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhansen Barreto
 */
@RestController
@RequestMapping("/alunos/{alunoId}/avaliacoes")
public class AlunoAvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaService service;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AvaliacaoFisicaConvert convert;

    @GetMapping
    public List<AvaliacaoFisicaOutput> listar(@PathVariable Long alunoId) {
        return convert.toListOutput(alunoService.buscarPorId(alunoId).getAvaliacoes());
    }

    @GetMapping("/{avaliacaoId}")
    public AvaliacaoFisicaOutput buscarPorIds(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        return convert.toOutput(
                service.buscarPorIds(alunoService.buscarPorId(alunoId).getId(), avaliacaoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoFisicaOutput salvar(@PathVariable Long alunoId, @RequestBody @Valid AvaliacaoFisicaInput input) {
        var avaliacao = new AvaliacaoFisica();

        avaliacao.setAluno(alunoService.buscarPorId(alunoId));
        avaliacao.setPeso(input.getPeso());
        avaliacao.setAltura(input.getAltura());

        return convert.toOutput(service.salvar(avaliacao));
    }

    @DeleteMapping("/{avaliacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long alunoId, @PathVariable Long avaliacaoId) {
        service.excluir(alunoService.buscarPorId(alunoId).getId(), avaliacaoId);
    }
}
