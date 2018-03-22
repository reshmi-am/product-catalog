package catalog;

import java.sql.SQLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(assignableTypes = MainController.class)
public class GlobalExceptionHandler {

	@ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleDBError(RuntimeException ex, WebRequest request) {
		 return new ResponseEntity<Object>(
		          "Data error", new HttpHeaders(), HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler({ HttpMessageNotReadableException.class })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
		 return new ResponseEntity<Object>(
		          "Invalid input", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllError(RuntimeException ex, WebRequest request) {
		 return new ResponseEntity<Object>(
		          "Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
