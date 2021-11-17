package com.br.itapemirim.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Error {
	
	@JsonProperty("Mensagem")
    private String message;

	public Error(String message) {
		super();
		this.message = message;
	}
}
