package com.wergnet.wergnetoil.api.exceptionHandler;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WergnetOilExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {

		String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();

		List<Erro> errors = Arrays.asList(new Erro(userMessage, developerMessage));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.operation-not-allowed", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
	
		List<Erro> errors = Arrays.asList(new Erro(userMessage, developerMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {
		
		private String userMessage;
		private String developerMessage;
		
		public Erro(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;
		}
		
		public String getUserMessage() {
			return userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}
	}

}
