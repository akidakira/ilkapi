package com.example.ilkapi.exception;

import java.util.List;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.ilkapi.apiresponse.ApiResponse;
import com.example.ilkapi.apiresponse.ResponseType;

import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	//genel hatalar
	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<ApiError> exceptionhandler(BaseException ex){
		
		ResponseType responseType = ex.getResponseType();
		
		ApiError apiError = new ApiError(
				responseType.getCode(),
				responseType.getMessage(),
				LocalDateTime.now(),
				null
				);
		
		return ResponseEntity.status(responseType.getStatus()).body(apiError);
	}
	
	//validasyon hataları
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	public Map<String, String> validationHandler(MethodArgumentNotValidException ex){
		
		Map<String, String> validMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			validMap.put(error.getField(), error.getDefaultMessage());
		});
		
		return validMap;
	}
}
