package br.com.spring.fastlog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	//Declara que essa classe é um componente Spring, com o objetivo de definição/configuração de Beans
public class ModelMapperConfig {

	@Bean	 // Indica que esse método instancia, inicia e configura um Beam que será gerenciado pelo Spring e portanto será disponibilizado para injeção em outras classes
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
}
