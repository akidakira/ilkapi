package com.example.ilkapi.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

	//client için hata modeli
	private String code;
	private String message;
	private LocalDateTime timestamp;
	private Map<String, List<String>> errors; //hataları toplamak için
	
	/*public ApiError(String status, String message, Map<String, List<String>> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}*/
}
