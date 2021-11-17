package com.br.itapemirim.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightDTO {
	
	private Long id;
	@NotNull(message = "Campo Código Aeronave Obrigatório")
    private String codFlight;
	private String nameFlight;
	private LocalDate dateFuel;
}
