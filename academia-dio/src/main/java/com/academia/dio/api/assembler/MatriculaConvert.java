package com.academia.dio.api.assembler;

import com.academia.dio.api.dto.MatriculaOutput;
import com.academia.dio.domain.model.Matricula;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jhansen Barreto
 */
@Component
public class MatriculaConvert {

    @Autowired
    private ModelMapper mapper;

    public MatriculaOutput toOutput(Matricula objectModel) {
        return mapper.map(objectModel, MatriculaOutput.class);
    }

    public List<MatriculaOutput> toListOutput(Collection<Matricula> collection) {
        return collection.stream().map(object -> toOutput(object)).collect(Collectors.toList());
    }
}
