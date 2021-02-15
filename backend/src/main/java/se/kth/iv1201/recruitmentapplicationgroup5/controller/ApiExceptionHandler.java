package se.kth.iv1201.recruitmentapplicationgroup5.controller;

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
	
	//Logger here
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMsgBody> handleValidationException(
			MethodArgumentNotValidException e, 
			WebRequest req)
	{
		var status = HttpStatus.BAD_REQUEST;
		String url = extractUrl(req);
		String errorMsg = generateErrMsg(e);
		var body = new ErrorMsgBody(status.value(), url, errorMsg);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	private String extractUrl(WebRequest req) {
		return ((ServletWebRequest)req).getRequest().getRequestURL().toString();
	}
	
	private String generateErrMsg(MethodArgumentNotValidException e) {
		return e.getFieldErrors().stream()
				.map(error -> error.getDefaultMessage())
				.reduce("", (acc, element) -> acc + element + "; ");
	}
	
	
}
