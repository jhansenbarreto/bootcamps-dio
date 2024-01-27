package com.academia.dio.api.assembler;

import com.academia.dio.api.dto.AlunoInput;
import com.academia.dio.api.dto.AlunoOutput;
import com.academia.dio.domain.model.Aluno;
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
public class AlunoConvert {

    @Autowired
    private ModelMapper mapper;

    public AlunoOutput toOutput(Aluno objectModel) {
        return mapper.map(objectModel, AlunoOutput.class);
    }

    public Aluno toObjectModel(AlunoInput input) {
        return mapper.map(input, Aluno.class);
    }

    public List<AlunoOutput> toListOutput(Collection<Aluno> collection) {
        return collection.stream().map(object -> toOutput(object)).collect(Collectors.toList());
    }

    public void copyToObjectModel(AlunoInput input, Aluno objectModel) {
        mapper.map(input, objectModel);
    }
}
