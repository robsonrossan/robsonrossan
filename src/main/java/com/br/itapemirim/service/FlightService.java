package com.br.itapemirim.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.itapemirim.ConverterObject.ConverterObject;
import com.br.itapemirim.dto.FlightDTO;
import com.br.itapemirim.dto.FilterReportFlightDTO;
import com.br.itapemirim.entity.FlightEntity;
import com.br.itapemirim.error.BusinessException;
import com.br.itapemirim.repository.FlightRepository;
import com.br.itapemirim.response.FlightResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	/**
	 * Cadastrar Voo
	 * @param flightDTO
	 * @return Mensagenm do Cadastro
	 */
    public FlightResponse registerFlight(FlightDTO flightDTO) {

    	FlightResponse response = new FlightResponse();
    	FlightEntity flightEntity = ConverterObject.parseObject(flightDTO, FlightEntity.class);
    	flightRepository.save(flightEntity);
    	
    	response.setMsgRigisterFlight("Cadastro inserido com sucesso.");
    	log.info(" ################### Cadastro inserido com sucesso. ###################");
		return response;
	}

    /**
     * Lista de Voos
     * @param flightDTO
     * @param pageable
     * @return Retorna todos os Voo abastecidos
     */
	public Page<FlightDTO> getAllFueledPlanes(FilterReportFlightDTO flightDTO, Pageable pageable) {
		return ConverterObject.mapEntityPageIntoDtoPage(flightRepository.findAll(pageable), FlightDTO.class);
	}
	
	/**
	 * Consulta Por ID
	 * @param id
	 * @return Voo Abastecido
	 */
	public FlightDTO findFlightById(Long id) {
		
		Optional<FlightEntity> entity = flightRepository.findById(id);

		if(!entity.isPresent()) {
			throw new BusinessException("Registro não encontrado");
		}
		FlightDTO dto = ConverterObject.parseObject(entity.get(), FlightDTO.class);
		return dto;
	}
	
	/**
	 * Atualiza os Voos 
	 * @param flightDTO
	 * @return Mensagem 
	 */
	public FlightResponse updateFlight(FlightDTO flightDTO) {

		FlightResponse response = new FlightResponse();
		this.findFlightById(flightDTO.getId());

		FlightEntity controlFlight = ConverterObject.parseObject(flightDTO, FlightEntity.class);
		flightRepository.save(controlFlight);
		response.setMsgRigisterFlight("Registro atualizado com sucesso.");

		return response;
	}
	
    /**
     * Excluir registro de voo
     * @param idFlight
     */
    public FlightResponse deleteFlight(Long idFlight) {
    	FlightResponse response = new FlightResponse();
    	flightRepository.deleteById(idFlight);
    	response.setMsgRigisterFlight("Registro excluído com sucesso.");
		return response;
    }
}
