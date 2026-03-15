package com.example.ilkapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ilkapi.apiresponse.ApiResponse;
import com.example.ilkapi.apiresponse.ResponseType;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;
import com.example.ilkapi.service.IEmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rest/api")
public class EmployeeControllerImpl implements IEmployeeController{

	@Autowired
	private IEmployeeService employeeService;
	
	@Override
	@PostMapping(path = "/create/employee")
	public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@Valid @RequestBody EmployeeDtoIU employee) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.EMPLOYEE_CREATED, employeeService.createEmployee(employee)));
	}

	@Override
	@GetMapping(path = "/findbyid/{id}")
	public ResponseEntity<ApiResponse<EmployeeDto>> findByIdEmployee(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.EMPLOYEE_FOUND, employeeService.findByIdEmployee(id)));
	}
	
	@Override
	@GetMapping(path = "/getall/employee")
	public ResponseEntity<ApiResponse<List<EmployeeDto>>> getAllEmployees() {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.LIST_FOUND, employeeService.getAllEmployees()));
	}

	@Override
	@DeleteMapping(path = "/delete/employee/byid/{id}")
	public ResponseEntity<ApiResponse<Boolean>> deleteByIdEmployee(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.EMPLOYEE_DELETED, employeeService.deleteByIdEmployee(id)));
	}

	@Override
	@PutMapping(path = "/update/employee/byid/{id}")
	public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployeeById(@PathVariable("id") Long id, @RequestBody EmployeeDtoIU employee) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.EMPLOYEE_UPDATED, employeeService.updateEmployeeById(id, employee)));
	}

}
