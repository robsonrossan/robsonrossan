package com.br.itapemirim.controller;

import static org.springframework.http.HttpStatus.OK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.br.itapemirim.dto.FlightDTO;
import com.br.itapemirim.dto.FilterReportFlightDTO;
import com.br.itapemirim.dto.ResultReportFlightFueledDTO;
import com.br.itapemirim.response.FlightResponse;
import com.br.itapemirim.response.PageResponse;
import com.br.itapemirim.service.FlightService;
import com.br.itapemirim.service.ReportFlightService;

@RestController
@RequestMapping("/itapemirim")
public class FlightController {
	
	@Autowired
	private FlightService serviceFlight;
	
	@Autowired
	private ReportFlightService reportService;
	
	/**
	 * Cadastro
	 * @param flightDTO
	 * @return Mensagem do Cadastro
	 */
    @PostMapping("/register")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<FlightResponse> registerFlight(@RequestBody @Validated FlightDTO flightDTO) {
        return ResponseEntity.ok(serviceFlight.registerFlight(flightDTO));
    }
    
    /**
     * Consulta Voo por ID
     * @param idFlight
     * @return Voo
     * @throws Exception
     */
    @GetMapping(value = "/getflightbyid")
	@ResponseStatus(value = OK)
	@CacheEvict(value="controlFlight", allEntries=true)
    public ResponseEntity<FlightDTO> findByIdFueledPlanes(@RequestParam(required = true) Long idFlight) throws Exception {
    	FlightDTO dto = serviceFlight.findFlightById(idFlight);
        return ResponseEntity.ok(dto);
	}
	
    /**
     * Consulta com paginação e parâmetros
     * @param filter
     * @param pageable
     * @return Lista paginada da consulta
     * @throws Exception
     */
    @PostMapping(value = "/getbyparamfueledplanes")
	@ResponseStatus(value = OK)
    @CacheEvict(value="controlFlight", allEntries=true)
	public ResponseEntity<PageResponse<ResultReportFlightFueledDTO>> getFueledPlanes(
			@RequestBody FilterReportFlightDTO filter, 
			Pageable pageable) throws Exception  {
    	filter.validateFilter();
		Page<ResultReportFlightFueledDTO>  responseFueledPlanes = reportService.getFueledPlanes(filter, pageable);
		PageResponse<ResultReportFlightFueledDTO> pageResponse = new PageResponse<ResultReportFlightFueledDTO>(
				responseFueledPlanes.getNumber(),
				responseFueledPlanes.getSize(),
				responseFueledPlanes.getTotalElements(),
				responseFueledPlanes.getContent());
		return ResponseEntity.ok(pageResponse);
	}
    
    /**
     * Consulta todos os registros com paginação
     * @param dto
     * @param pageable
     * @return Lista de todos os registros
     * @throws Exception
     */
    @PostMapping(value = "/getallfueledplanes")
	@ResponseStatus(value = OK)
    @CacheEvict(value="controlFlight", allEntries=true)
	public ResponseEntity<PageResponse<FlightDTO>> getAllFueledPlanes(
			@RequestBody FilterReportFlightDTO dto, 
			Pageable pageable) throws Exception  {

		dto.validateFilter();
		Page<FlightDTO>  responseFueledPlanes = serviceFlight.getAllFueledPlanes(dto, pageable);
		PageResponse<FlightDTO> pageResponse = new PageResponse<FlightDTO>(
				responseFueledPlanes.getNumber(),
				responseFueledPlanes.getSize(),
				responseFueledPlanes.getTotalElements(),
				responseFueledPlanes.getContent());
		return ResponseEntity.ok(pageResponse);
	}
    
	@PutMapping(value = "/updateflight")
	@ResponseStatus(value = OK)
	@CacheEvict(value="controlFlight", allEntries=true)
    public ResponseEntity<FlightResponse> updateFlight(@RequestBody @Validated FlightDTO flightDTO) {
        return ResponseEntity.ok(serviceFlight.updateFlight(flightDTO));
    }
    
	@DeleteMapping(value = "/deleteflight")
	@ResponseStatus(value = OK)
	@CacheEvict(value="controlFlight", allEntries=true)
    public ResponseEntity<FlightResponse> deleteFueledPlanes(@RequestParam(required = true) Long idFlight) throws Exception {
        return ResponseEntity.ok(serviceFlight.deleteFlight(idFlight));
    }
}
