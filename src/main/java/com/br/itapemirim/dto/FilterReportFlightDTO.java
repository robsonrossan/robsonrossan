package com.br.itapemirim.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.br.itapemirim.error.BusinessException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterReportFlightDTO {

    private String codFlight;
	private String nameFlight;
	private LocalDate dateFuelStart;
	private LocalDate dateFuelEnd;
	
	public void validateFilter() {
		if(Objects.isNull(dateFuelStart) || Objects.isNull(dateFuelEnd) || dateFuelStart.isAfter(dateFuelEnd)) {
			throw new BusinessException("Favor informar um período de data válido.");
		}
	}
}
