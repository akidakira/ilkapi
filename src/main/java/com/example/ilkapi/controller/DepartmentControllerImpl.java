package com.example.ilkapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.DepartmentDtoIU;
import com.example.ilkapi.service.IDepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rest/api")
public class DepartmentControllerImpl implements IDepartmentController{
	
	@Autowired
	private IDepartmentService departmentService;

	@Override
	@PostMapping(path = "/create/department")
	public ResponseEntity<ApiResponse<DepartmentDto>> createDepartment(@Valid @RequestBody DepartmentDtoIU department) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.DEPARTMENT_CREATED, departmentService.createDepartment(department)));
	}

	@Override
	@DeleteMapping(path = "/delete/department/byid/{id}")
	public ResponseEntity<ApiResponse<Boolean>> deleteDepartmentById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.DEPARTMENT_DELETED, departmentService.deleteDepartmentById(id)));
	}

	@Override
	@GetMapping(path = "/get/department/list")
	public ResponseEntity<ApiResponse<List<DepartmentDto>>> getDepartmentList() {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.LIST_FOUND, departmentService.getDepartmentList()));
	}

	@Override
	@GetMapping(path = "/department/findbyid/{id}")
	public ResponseEntity<ApiResponse<DepartmentDto>> findByIdDepartment(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.DEPARTMENT_FOUND, departmentService.findByIdDepartment(id)));
	}

	@Override
	@PutMapping(path = "/update/department/byid/{id}")
	public ResponseEntity<ApiResponse<DepartmentDto>> updateDepartmentById(@PathVariable("id") long id,@Valid @RequestBody DepartmentDtoIU department) {
		return ResponseEntity.ok(ApiResponse.success(ResponseType.DEPARTMENT_UPDATED, departmentService.updateDepartmentById(id, department)));
	}

	

}
