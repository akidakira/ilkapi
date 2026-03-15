package com.example.ilkapi.apiresponse;

import java.nio.channels.NonReadableChannelException;

import org.hibernate.boot.model.internal.Nullability;
import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResponseType {
	
	//sadece doğru ve başarılı işlemler ek bir şey istenmeden controllerdan ok metodu ile çağırılır
	//ancak hata olduğunda ve olacaksa bu iş service kısmında olmalı ve gerektiğinde kontrol edilmeli
	
	// -- SİSTEM HATALARI
	INTERNAL_SERVER_EXCEPTİON("INTERNAL_SERVER_EXCEPTİON","Unexcepted Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
	
	// -- VALİDASYON HATALARI
	VALIDATION_ERROR("VALIDATION_ERROR","Validation Error", HttpStatus.BAD_REQUEST),
	
	// -- BAŞARILI İŞLEMLER
	EMPLOYEE_CREATED("EMPLOYEE_CREATED","Employee Successfully Created", HttpStatus.CREATED),
	DEPARTMENT_CREATED("DEPARTMENT_CREATED","Department Successfully Created", HttpStatus.CREATED),
	
	EMPLOYEE_UPDATED("EMPLOYEE_UPDATED","Employee Successfully Updated",HttpStatus.OK),
	DEPARTMENT_UPDATED("DEPARTMENT_UPDATED","Department Successfully Updated", HttpStatus.OK),
	
	LIST_FOUND("LIST_FOUND","List Successfully Found", HttpStatus.FOUND),
	EMPLOYEE_FOUND("EMPLOYEE_FOUND","Employee Successfully Found", HttpStatus.FOUND),
	DEPARTMENT_FOUND("DEPARTMENT_FOUND","Department Successfuly Found", HttpStatus.FOUND),
	
	EMPLOYEE_DELETED("EMPLOYEE_DELETED","Employee Successfully Deleted", HttpStatus.OK),
	DEPARTMENT_DELETED("DEPARTMENT_DELETED","Department Successfully Deleted", HttpStatus.OK),
	
	// -- BAŞARISIZ İŞLEMLER
	LIST_NOT_FOUND("LIST_NOT_FOUND","List Not Found", HttpStatus.NOT_FOUND),
	EMPLOYEE_NOT_FOUND("EMPLOYEE_NOT_FOUND","Employee Not Found", HttpStatus.NOT_FOUND),
	DEPARTMENT_NOT_FOUND("DEPARTMENT_NOT_FOUND","Department Not Found", HttpStatus.NOT_FOUND);
	
	private String code;
	private final String message;
	private HttpStatus status;
	
	private ResponseType(String code,String message, HttpStatus status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
}
