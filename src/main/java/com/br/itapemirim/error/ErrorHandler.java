package com.br.itapemirim.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> processValidationRestError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<Error>(processFieldErrors(fieldErrors), BAD_REQUEST);
    }
	
	@ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<?> processValidationError(BindException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<Error>(processFieldErrors(fieldErrors), BAD_REQUEST);
    }
	
//	@ExceptionHandler(AcessoNegadoException.class)
//	public ResponseEntity<Erro> erro(AcessoNegadoException e) {
//		log.error("Erro inesperado.", e);
//		return new ResponseEntity<Erro>(new Erro(e.getMessage()), UNAUTHORIZED);
//	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> erro(Exception e) {
		log.error("Erro inesperado.", e);
		return new ResponseEntity<Error>(new Error(e.getMessage()), INTERNAL_SERVER_ERROR);
	}

    private Error processFieldErrors(List<FieldError> fieldErrors) {
    	
        for (FieldError fieldError: fieldErrors) {
            return new Error("Erro ao ler o campo " + fieldError.getField());            
        }

        return new Error("Erro interno. Favor contactar o suporte.");
    }
}
