package com.br.itapemirim.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CAD_FLIGHT")
@Setter
@Getter
@NoArgsConstructor
public class FlightEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_FLIGHT")
	private Long id;

	@Column(name = "COD_FLIGHT")
	private String codFlight;
	
	@Column(name = "NAME_FLIGHT")
	private String nameFlight;
	
	@Column(name = "DATE_FUEL")
	private LocalDate dateFuel;
}
