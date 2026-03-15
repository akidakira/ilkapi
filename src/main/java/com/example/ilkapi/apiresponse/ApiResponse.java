package com.example.ilkapi.apiresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse <T>{
	
	private String code;
	private String message;
	private T data;
	
	public static <T> ApiResponse<T> success(ResponseType type,T data){
		return new ApiResponse<>(type.getCode(),type.getMessage(), data);
	}
	
	public static <T> ApiResponse<T> error(ResponseType type){
		return new ApiResponse<T>(type.getCode(), type.getMessage(), null);
	}
	

}
