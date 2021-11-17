package com.br.itapemirim.ConverterObject;

import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import com.br.itapemirim.dto.FlightDTO;
import com.br.itapemirim.entity.FlightEntity;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class ConverterObject {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	/**
	 * @param <O>
	 * @param <D>
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}	
	
	/** Convert Entity to Object
	 * @param <D>
	 * @param <T>
	 * @param entities
	 * @param dtoClass
	 * @return Page<DTO>
	 */
	public static <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
	    return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
	}

	public static List<FlightDTO> parseListObjects(List<FlightEntity> fueledPlanes) {
		List<FlightDTO> listaFueledPlanes = Arrays.asList(modelMapper.map(fueledPlanes, FlightDTO[].class));
		return listaFueledPlanes;
	} 
}