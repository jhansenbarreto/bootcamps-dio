package com.academia.dio.core.config;

import com.academia.dio.api.dto.AvaliacaoFisicaOutput;
import com.academia.dio.domain.model.AvaliacaoFisica;
import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Jhansen Barreto
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        var avaliacaoMapper = mapper.createTypeMap(AvaliacaoFisica.class, AvaliacaoFisicaOutput.class);
        avaliacaoMapper.<String>addMapping(
                item -> item.getIMC(),
                (destino, valor) -> destino.setImc(valor)
        );
        return mapper;
    }
}
