package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Class for encapsulating non default exception handling in one place.
 *
 */
@ControllerAdvice
public class ApiExceptionHandler //extends ResponseEntityExceptionHandler 
{
	
	//Logger here as a private field
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(
			MethodArgumentNotValidException e, 
			WebRequest req)
	{
		var status = HttpStatus.BAD_REQUEST;
		String url = extractUrl(req);
		String errorMsg = generateErrMsg(e);
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
	
	private ResponseEntity<Object> handleInternalError(Exception e, WebRequest req) {
		//Log error here instead of System.out
		System.out.println(e);
		System.out.println(req);
						
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
				.map(error -> error.getDefaultMessage())
				.reduce("", (acc, element) -> acc + element + "; ");
	}
	
	private ErrorMsgBody createErrorBody(HttpStatus status, String url, String msg) {
		return new ErrorMsgBody(status.value(), url, msg);
	}
	
	
}
