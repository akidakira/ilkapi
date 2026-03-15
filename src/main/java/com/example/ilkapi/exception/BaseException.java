package com.example.ilkapi.exception;

import com.example.ilkapi.apiresponse.ResponseType;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
public class BaseException extends RuntimeException{
	
	private final ResponseType responseType;
	
	public BaseException(ResponseType responseType) {
		super(responseType.getMessage());
		this.responseType = responseType;
	}

}
