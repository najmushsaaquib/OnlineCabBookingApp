package com.masai.Exception;



import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(CustomerException ie,WebRequest wr)  {
		System.out.println("inside myHandler method...RNE");
		
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), wr.getDescription(false));
		
		
	
	 return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);		
		
	}
	
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {
		System.out.println("inside myHandler method...NHFE");

		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));

	return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
				
}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandlerMain(Exception ie,WebRequest wr)  {
		System.out.println("inside myHandler method...EXP");
		

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), wr.getDescription(false));
				
	
	 return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);		
				
	}
	


	
	
}

