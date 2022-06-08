package br.com.spring.fastlog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.spring.fastlog.api.model.EntregaDto;
import br.com.spring.fastlog.api.model.input.EntregaInput;
import br.com.spring.fastlog.domain.model.Entrega;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component	//Componente do Spring, tipo gerenciado pelo Spring
public class EntregaAssembler {

	private ModelMapper modelMapper;
	
	public EntregaDto toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDto.class);
	}
	
	public List<EntregaDto> toCollectionmodel(List<Entrega> entregas){
		return entregas.stream()
				.map(this:: toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
