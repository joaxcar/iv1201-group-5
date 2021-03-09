package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import java.util.Arrays;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Class for encapsulating non default exception handling in one place.
 *
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(
			MethodArgumentNotValidException e, 
			WebRequest req)
	{
		log.debug(e.getMessage());
		var status = HttpStatus.BAD_REQUEST;
		String url = extractUrl(req);
		String errorMsg = generateErrMsg(e);
		var body = createErrorBody(status, url, errorMsg);

		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleJsonParseException(
			HttpMessageNotReadableException e, 
			WebRequest req) 
	{
		log.debug(e.getMessage());
		var status = HttpStatus.BAD_REQUEST;
		String url = extractUrl(req);
		var errorMsg = "Request body invalid JSON";
		var body = createErrorBody(status, url, errorMsg);

		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleContraintViolationException(
			ConstraintViolationException e,
			WebRequest req)
	{
		return handleInternalError(e, req);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest req) {
		return handleInternalError(e, req);
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e, WebRequest req) {
		log.debug(e.getMessage());
		var status = HttpStatus.BAD_REQUEST;
		String url = extractUrl(req);
		String errorMsg = e.getMessage();
		var body = createErrorBody(status, url, errorMsg);
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e, WebRequest req) {
		log.debug(e.getMessage());
		var status = HttpStatus.BAD_REQUEST;
		String url = extractUrl(req);
		String errorMsg = "Invalid user credentials.";
		var body = createErrorBody(status, url, errorMsg);
		return ResponseEntity.status(status).body(body);
	}
	
	private ResponseEntity<Object> handleInternalError(Exception e, WebRequest req) {
		log.debug(e.getMessage());
		log.debug(Arrays.toString(e.getStackTrace()).replaceAll(",", "\n"));
						
		var status = HttpStatus.INTERNAL_SERVER_ERROR;
		String url = extractUrl(req);
		String errorMsg = "Something went wrong on our end. Try again.";
		var body = createErrorBody(status, url, errorMsg);
		return ResponseEntity.status(status).body(body);
	}
	
	private String extractUrl(WebRequest req) {
		return ((ServletWebRequest)req).getRequest().getRequestURL().toString();
	}
	
	private String generateErrMsg(MethodArgumentNotValidException e) {
		return e.getFieldErrors().stream()
				.map(FieldError::getDefaultMessage)
				.reduce("", (acc, element) -> acc + element + "; ");
	}
	
	private ErrorMsgBody createErrorBody(HttpStatus status, String url, String msg) {
		return new ErrorMsgBody(status.value(), url, msg);
	}
	
	
}
