package com.example.ilkapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ilkapi.apiresponse.ApiResponse;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.DepartmentDtoIU;


public interface IDepartmentController {

	public ResponseEntity<ApiResponse<DepartmentDto>> createDepartment(DepartmentDtoIU department);
	public ResponseEntity<ApiResponse<Boolean>> deleteDepartmentById(Long id);
	public ResponseEntity<ApiResponse<List<DepartmentDto>>> getDepartmentList();
	public ResponseEntity<ApiResponse<DepartmentDto>> findByIdDepartment(Long id);
	public ResponseEntity<ApiResponse<DepartmentDto>> updateDepartmentById(long id, DepartmentDtoIU department);
}
