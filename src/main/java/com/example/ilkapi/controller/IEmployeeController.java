package com.example.ilkapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ilkapi.apiresponse.ApiResponse;
import com.example.ilkapi.dto.DepartmentDtoIU;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;

import jakarta.validation.Valid;

public interface IEmployeeController {

	public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(EmployeeDtoIU employee);
	public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployeeById(Long id, EmployeeDtoIU employee);
	public ResponseEntity<ApiResponse<EmployeeDto>> findByIdEmployee(Long id);
	public ResponseEntity<ApiResponse<List<EmployeeDto>>> getAllEmployees();
	public ResponseEntity<ApiResponse<Boolean>>  deleteByIdEmployee(Long id);
	
}
