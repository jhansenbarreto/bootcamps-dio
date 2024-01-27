package com.academia.dio.api.controller;

import com.academia.dio.api.assembler.MatriculaConvert;
import com.academia.dio.api.dto.MatriculaOutput;
import com.academia.dio.domain.model.Matricula;
import com.academia.dio.domain.service.AlunoService;
import com.academia.dio.domain.service.MatriculaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhansen Barreto
 */
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MatriculaConvert convert;

    @GetMapping
    public List<MatriculaOutput> listar() {
        return convert.toListOutput(service.listarTodas());
    }
    
    @GetMapping("/{data1}/{data2}")
    public List<MatriculaOutput> listarPorDatas(@PathVariable LocalDate data1, @PathVariable LocalDate data2) {
        return convert.toListOutput(service.buscarEntreDatas(data1.atStartOfDay(), data2.atStartOfDay()));
    }

    @GetMapping("/aluno/{alunoId}")
    public List<MatriculaOutput> listarPorAluno(@PathVariable Long alunoId) {
        var aluno = alunoService.buscarPorId(alunoId);
        return convert.toListOutput(service.listarPorAlunoId(aluno.getId()));
    }

    @GetMapping("/{id}")
    public MatriculaOutput buscar(@PathVariable Long id) {
        return convert.toOutput(service.buscarPorId(id));
    }

    @PostMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaOutput matricular(@PathVariable Long alunoId) {
        var matricula = new Matricula();
        matricula.setAluno(alunoService.buscarPorId(alunoId));

        return convert.toOutput(service.matricular(matricula));
    }

    @PutMapping("/{alunoId}")
    public MatriculaOutput desmatricular(@PathVariable Long alunoId) {
        var aluno = alunoService.buscarPorId(alunoId);
        return convert.toOutput(service.desmatricular(aluno.getId()));
    }
}
