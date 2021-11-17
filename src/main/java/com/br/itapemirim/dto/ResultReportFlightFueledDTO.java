package com.br.itapemirim.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.Data;

@Data
public class ResultReportFlightFueledDTO {

	private long id;
    private String codFlight;
	private String nameFlight;
	private LocalDate date;
	
	public ResultReportFlightFueledDTO(Object[] objReturn) {
		this.id = ((Number) objReturn[0]).longValue();
		this.codFlight = (String) objReturn[1];
		this.nameFlight = (String) objReturn[2];
		this.date = this.convertToLocal((Date) objReturn[3]);
	}
	
	public LocalDate convertToLocal(Date dateSql) {
	    return Instant.ofEpochMilli(dateSql.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
