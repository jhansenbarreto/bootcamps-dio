package com.academia.dio.api.assembler;

import com.academia.dio.api.dto.AvaliacaoFisicaOutput;
import com.academia.dio.domain.model.AvaliacaoFisica;
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
public class AvaliacaoFisicaConvert {

    @Autowired
    private ModelMapper mapper;

    public AvaliacaoFisicaOutput toOutput(AvaliacaoFisica objectModel) {
        return mapper.map(objectModel, AvaliacaoFisicaOutput.class);
    }

    public List<AvaliacaoFisicaOutput> toListOutput(Collection<AvaliacaoFisica> collection) {
        return collection.stream().map(object -> toOutput(object)).collect(Collectors.toList());
    }
}
